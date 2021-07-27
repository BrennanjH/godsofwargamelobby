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
    public PreGameEnd(GodsofWargame GameState){
        gameState = GameState;
        //removed = remove;
    }
    @Override
    public void execute(UnitTypes remove){
        System.out.println("GameEnd Has been called");
        removed = (UnitCommandStructure) remove;
        PlayerToSpectator spectate = new PlayerToSpectator(gameState);
        try {
            if(isLastCommander(gameState)){
                sendCloseStatement closer = new sendCloseStatement(gameState);
                closer.sendCustomCloseMessage("WIN CONDITION MET");
                JSONHandler passer = new CommandHandler(gameState);
                //HashMap<String, String> serializedEnd = new HashMap<>();
                //serializedEnd = passer.convertToString(closer.sendCustomCloseMessage("WIN CONDITION MET"), serializedEnd);
                DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize(closer.sendCustomCloseMessage("WIN CONDITION MET")));
                endGame();
            }
        }
        catch ( IOException E){
            System.err.println("execute in PreEndGame has thrown IOException");
        }
    }
    private boolean isLastCommander(GodsofWargame gameState){
        return gameState.getCommanders().size() <= 1; 
    }
    //Closes all sessions in gameState
    private void endGame() throws IOException{
        for (String s : gameState.getClients().keySet() ){
            gameState.getClients().get(s).close();
        }
    }
    
}
