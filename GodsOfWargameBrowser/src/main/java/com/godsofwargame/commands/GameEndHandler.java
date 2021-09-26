/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import Faction.Team;
import Location.Territory;
import com.godsofwargame.backend.GodsofWargame;
import java.io.IOException;
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
        
        
        try {
            //Check if player has remaining command Units
            
            if(isPlayersLastCommander(gameState)){
                sendCloseStatement closer = new sendCloseStatement(gameState);
                System.out.println("IsPlayerLast Commander: true");
                //Send Player to Spectator Role/remove them
                closer.sendCustomCloseMessage("Player: " + removed + " Has been defeated");
               
                
                gameState.removeSession(gameState.getClients().get(removed));
                //Delete empty teams if present and remove their land
                cleanUpEmptyTeams();
                //Check if any other players remain
                if(isLastPlayer()){
                    System.out.println("IsLastPlayer: true");
                    //create custom ending message
                    
                    closer.sendCustomCloseMessage("Game over! winner has been decided" );
                    //end the game by removing all players
                    endGame();
                }
                
                
            }
        }
        catch ( IOException E){
            System.err.println("execute in PreEndGame has thrown IOException");
        }
    }
    public void playerLost(){
        try {
            sendCloseStatement closer = new sendCloseStatement(gameState);
            //closer.sendCustomCloseMessage("Player: " + removed + " Has left the game");
            gameState.removeSession(gameState.getClients().get(removed));
            
            //Remove empty teams and delete their land
            cleanUpEmptyTeams();
            //Check if any other players remain
            if(gameState.getReadyStates().isFullyLoaded()){
                
                if(isLastPlayer()){
                    System.out.println("IsLastPlayer: true");
                    //create custom ending message
                    
                    closer.sendCustomCloseMessage("Game over! winner has been decided" );
                    //end the game by removing all players
                    endGame();
                }
            }
        } catch ( IOException E){
            System.err.println("playerLost in PreEndGame has thrown IOException");
        }
    }
    private boolean isPlayersLastCommander(GodsofWargame gameState){
        return !(gameState.getCommanders().containsKey(removed)); 
    }
    private boolean isLastPlayer(){
        System.out.println("last player keyset size: " + gameState.getCommanders().keySet().size());
        return gameState.getCommanders().keySet().size() <=1;
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
            gameState.removeSession(gameState.getClients().get(s));
        }
        for (Territory[] landOwnership : gameState.getMapState().getLandOwnership()) {
            for (int j = 0; j < landOwnership.length; j++) {
                //TODO update this method to try and give teams resources to the new team created by process
                //set all territory back to null
                landOwnership[j] = null;
            }
        }
        
    }
    //A method to remove all teams that are empty and territories associated with them
    private void cleanUpEmptyTeams(){
        System.out.println("GameEndHandler: cleanUpEmptyTeams: Entered");
        for (Team t : gameState.getFactions()){
            if (t.getTeam().isEmpty()){
                
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
    }
}
