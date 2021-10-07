/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import Faction.Member;
import Faction.Team;
import JSONOrienter.CommandHandler;
import JSONOrienter.JSONHandler;
import Location.Territory;
import com.godsofwargame.backend.DataDistributer;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.PlayerData;
import com.godsofwargame.backend.PlayerRole;
import com.godsofwargame.backend.SpectatorRole;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.websocket.Session;
/**
 * A class that is used to handle the removal of players both before and after gameState start
 * @author brenn
 */
public class GameEndHandler implements internalCommands{
    GodsofWargame gameState;
    //JSONhandler passer = new JSONhandler();
    String removed;//as gameEnding is related to 
    public GameEndHandler(String removed, GodsofWargame GameState){
        gameState = GameState;
        this.removed = removed;
        //removed = remove;
    }
    @Override
    public void execute(){
        System.out.println("PreGameEnd Has been called");
        
        System.out.println("GameEndHandler: execute: player being questioned: " + removed);
        
        //Check if player has remaining command Units
            
        if(isPlayersLastCommander(gameState)){
            //Send message to clients about the defeat of a player
            System.out.println("IsPlayerLast Commander: true");
            sendCloseStatement closer = new sendCloseStatement(gameState);
            closer.sendCustomCloseMessage("Player: " + removed + " Has been defeated");

            //Send Player to Spectator Role/remove them

            sendToSpecator(removed);


            //Check if any other players remain
            if(isLastTeam()){
                System.out.println("IsLastPlayer: true");
                //create custom ending message

                closer.sendCustomCloseMessage("Game over! winner has been decided" );
                //end the game by removing all players
                //endGame();
                resetGame();
            }
            JSONHandler passer = new CommandHandler(gameState);
            DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
        }
        
        
    }
    //Called when a player has been removed from the server for whatever reason, It is not called on a players usuall defeat
    public void playerLost(){
        try {
            sendCloseStatement closer = new sendCloseStatement(gameState);
            //closer.sendCustomCloseMessage("Player: " + removed + " Has left the game");
            
            //Safely remove the player from the server
            if(gameState.getClients().get(removed).isOpen()){
                gameState.getClients().get(removed).close();
            }
            gameState.removeSession(gameState.getClients().get(removed));
            
            cleanUpEmptyTeams();
            //Check if any other players remain
            if(gameState.getReadyStates().isFullyLoaded()){
                
                if(isLastPlayer()){
                    System.out.println("IsLastPlayer: true");
                    //create custom ending message
                    
                    closer.sendCustomCloseMessage("Game over! winner has been decided" );
                    
                    //Set the game back to it's starting point
                    resetGame();
                }
            }
        } catch ( IOException E){
            System.err.println("playerLost in PreEndGame has thrown IOException");
        }
        JSONHandler passer = new CommandHandler(gameState);
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
    }
    private boolean isPlayersLastCommander(GodsofWargame gameState){
        //System.out.println("Players remaining commanders: " + gameState.getCommanders().get(removed).size());
        System.out.println("Last: isPlayersLastCommander: player in question: " + removed);
        if( gameState.getCommanders().containsKey(removed)){
            if(gameState.getCommanders().get(removed).isEmpty()){
                System.out.println("last: 1");
                
                return true;
                
            } else {
                System.out.println("last: 2");
                System.out.println("commandlist size: " + gameState.getCommanders().get(removed).size());
                return false;
            }
        }
        System.out.println("last: 3");
        return true;
    }
    private boolean isLastPlayer(){
        System.out.println("last player keyset size: " + gameState.getCommanders().keySet().size());
        return gameState.getCommanders().keySet().size() <=1;
    }
    private boolean isLastTeam(){
        
        return (gameState.getFactions().size() <= 1);
    }
    
    //Instead of removing all the players it simply changes them all back to players, and sets up a new match
    private void resetGame(){
        
        //Change gameState & mapState back to preload state
        
        gameState.resetGameStateRetainPlayers();
        
        //Change player information back to starting state
        for(String s : gameState.getClients().keySet()){
            //reset all player Data
            Session temp = gameState.getClients().get(s);
            gameState.getClients().put(temp.getId(), temp);
            PlayerData newPlayerData = new PlayerData(temp.getId(), new PlayerRole(gameState));
            gameState.getMapState().addPlayer(temp.getId(), newPlayerData);//place the Id in a hashMap and the players information alongside it
        
            //Make sure all players are on a team
            JoinCoalitionCommand teamJoiner = new JoinCoalitionCommand();
            teamJoiner.setFactionName(temp.getId());
            teamJoiner.execute(gameState, temp.getId());
        }
        
        
    } 
    //A method to remove all teams that are empty and territories associated with them
    private void cleanUpEmptyTeams(){
        System.out.println("GameEndHandler: cleanUpEmptyTeams: Entered");
        List<Team> removeTeams = new ArrayList<>();
        for (Team t : gameState.getFactions()){
            if (t.getTeam().isEmpty()){
                removeTeams.add(t);
                //remove territory owned by team
                for (int i =0; i< gameState.getMapState().getLandOwnership().length;i++){
                    for(int j = 0; j < gameState.getMapState().getLandOwnership()[i].length;j++){ //TODO update this method to try and give teams resources to the new team created by process
                        //delete all territorys that use the team being removed 
                        
                        if(gameState.getMapState().getLandOwnership()[i][j] != null){
                            System.out.println("JoinCoalitionCommand: Execute: territory not equal to null");
                            if ( t.equals(gameState.getMapState().getLandOwnership()[i][j].getFaction())){
                                gameState.getMapState().getLandOwnership()[i][j] = null;
                            }
                        }
                    }
                }
            }
        }
        for(Team t : removeTeams){
            gameState.getFactions().remove(t);
        }
    }
    //Safely removes a player from their current role and sends them to specator with proper updates to the server
    private void sendToSpecator(String switchPlayer){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n"
                + " sendTo specatator has been called \n"
                + " specatator ID = " + switchPlayer );
                
        //Change role to spectator
        gameState.getMapState().getPlayer(switchPlayer).playerRole = new SpectatorRole(gameState);
        gameState.getMapState().getPlayer(switchPlayer).playerMember.setFactionName(null);
        //alter the server so that it no longer has information of user as a player
        gameState.getCommanders().remove(switchPlayer);
        gameState.removeMemberFromTeam(switchPlayer);
        gameState.getMapState().removeOwnerFromDeployedForces(switchPlayer);
        
        //Remove empty teams and delete their land
        cleanUpEmptyTeams();
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    //Closes all sessions in gameState and resets godsofWargame
    private void endGame() throws IOException{
        System.out.println("gameEndHandler: endGame: entered");
        String[] ids = new String[gameState.getClients().size()];
        System.out.println("gameEndHandler: endGame: client size "+ gameState.getClients().size());
        int intervals= 0;
        //Create a array of Strings that can be safely looped through
        for(String s : gameState.getClients().keySet()){
            ids[intervals] = s;
            intervals++;
        }
        //Using safe string[] close each sessionObject
        for(String s : ids){
            System.out.println("GameEndHandler: endGame: removeID: " + s);
            
            Session temp = gameState.getClients().get(s);
            if(temp.isOpen()){
                temp.close();
            }
            
        }
        for (Territory[] landOwnership : gameState.getMapState().getLandOwnership()) {
            for (int j = 0; j < landOwnership.length; j++) {
                //TODO update this method to try and give teams resources to the new team created by process
                //set all territory back to null
                landOwnership[j] = null;
            }
        }
        
    }
}
