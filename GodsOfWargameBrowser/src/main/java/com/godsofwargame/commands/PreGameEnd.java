/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import JSONOrienter.CommandHandler;
import JSONOrienter.JSONHandler;
import com.godsofwargame.backend.DataDistributer;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitCommandStructure;
import com.godsofwargame.backend.UnitTypes;
import java.io.IOException;
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
                System.out.println("IsPlayerLast Commander: true");
                //Check if any other players remain
                if(isLastPlayer()){
                    System.out.println("IsLastPlayer: true");
                    //create custom ending message
                    sendCloseStatement closer = new sendCloseStatement(gameState);
                    closer.sendCustomCloseMessage("Game over winner has been decided");
                    //end the game by removing all players
                    endGame();
                }
                //Send Player to Spectator Role/remove them
                //TODO
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
    //Closes all sessions in gameState
    private void endGame() throws IOException{
        /*
        for (String s : gameState.getClients().keySet() ){
            gameState.getClients().get(s).close();
        }
        */
    }
    
}
