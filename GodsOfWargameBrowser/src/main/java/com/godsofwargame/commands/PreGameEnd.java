/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import com.godsofwargame.backend.DataDistributer;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitCommandStructure;
import java.io.IOException;
import java.util.Set;
/**
 *Whenever a command Structure is lost this is called, It first checks the total number of command Structures remaining
 * If one or less remain the game will fully end, if more than one exists then whoever lost theirs is passed to PlayerToSpectator,
 * and nothing else is done with gameEnd until the next lost commandStructure.
 * @author brenn
 */
public class PreGameEnd implements internalCommands{
    GodsofWargame gameState;
    //JSONhandler passer = new JSONhandler();
    UnitCommandStructure removed;//as gameEnding is related to 
    public PreGameEnd(UnitCommandStructure remove, GodsofWargame GameState){
        gameState = GameState;
        removed = remove;
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
                closer.sendCustomCloseMessage("Player: " + removed.getOWNER() + " Has been defeated");
                
                gameState.removeSession(gameState.getClients().get(removed.getOWNER()));
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
    private boolean isPlayersLastCommander(GodsofWargame gameState){
        return !(gameState.getCommanders().containsKey(removed.getOWNER())); 
    }
    private boolean isLastPlayer(){
        System.out.println("last player keyset size: " + gameState.getCommanders().keySet().size());
        return gameState.getCommanders().keySet().size() <=1;
    }
    //Closes all sessions in gameState and resets godsofWargame
    private void endGame() throws IOException{
        
        String[] ids = new String[gameState.getClients().size()];
        int intervals= 0;
        //Create a array of Strings that can be safely looped through
        for(String s : gameState.getClients().keySet()){
            ids[intervals] = s;
            intervals++;
        }
        //Using safe string[] close each sessionObject
        for(String s : ids){
            gameState.removeSession(gameState.getClients().get(s));
        }
        System.out.println("PreGameEnd: endGame: client size: " + gameState.getClients().size());
        
        //Finalize shutdown of server
        gameState.cancelTimer();
        
    }
    
}
