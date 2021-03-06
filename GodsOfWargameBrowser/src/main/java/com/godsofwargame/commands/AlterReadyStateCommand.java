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

/** A command that sets the players ready state to true or false while also
 * performing validation to make sure it makes sense for a player to be on true or 
 * false
 *
 * @author brenn
 */
public class AlterReadyStateCommand implements commandInterface {
    //A boolean to represent weather or not a client is prepared to start the match
    //Will not be added to clients respective playerData until verification is complete
    boolean readyState;
    GodsofWargame gameState;
    //Change the users ready state, prepare Command Unit for construction
    @Override
    public void execute(GodsofWargame gameState, String Id) {
        System.out.println("AlterReadyStateCommand: Entered Execution");
        JSONHandler passer = new CommandHandler(gameState);
        this.gameState = gameState;
        
        //Check if server has already started
        if(gameState.getReadyStates().isFullyLoaded()){
            System.out.println("AlterReadyStateCommand: alteration failed, gameState already initialized");
            //Cancel command processing
            return;
        }
        //Check to see if pre-game setup is completed {creating and placing a commandUnit}
        if (readyState == true && validate(Id)){
            System.out.println("AlterReadyStateCommand: playerstate set to true");
            //Set readyState flag to true
            gameState.getMapState().getPlayer(Id).setReadyState(true);
            
            //Update players on new ready player
            DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
            
            
            //Check if all payers are ready
            if( allPlayersReady() ) {
                //start game
                gameState.load();
            }
        } else {
            System.out.println("AlterReadyStateCommand: playerstate set to false");
            //set readyState flag in PlayerData for user to false
            gameState.getMapState().getPlayer(Id).setReadyState(false);
            
            //update players that a player isn't ready
            DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
        }
        //Call Peerdiscrepency code to update all users immediatly when a user readys up
        
    }

    @Override
    public void setUnit(UnitTypes unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String testValue() {
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public boolean validate(String ID){
        return gameState.getCommanders().get(ID) != null;
        //return false;
    }
    //checks to see if any players have readystate set to false
    private boolean allPlayersReady(){
        //Spectators are automatically ready upon joining so they are not searched for
        for (String key : gameState.getMapState().getPlayers().keySet() ) {
            if ( !(gameState.getMapState().getPlayer(key).isReadyState())) {
                return false;
            }
        }
        return true;
    }
}
