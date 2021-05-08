/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import com.godsofwargame.backend.GodsofWargame;

/**
 *
 * @author brenn
 */
public class PlayerToSpectator {
    GodsofWargame gameState;
    public PlayerToSpectator(GodsofWargame GameState){
        gameState = GameState;
    }
    public void execute(String ID){
        //gameState.getClients().remove(ID);
        
    }
    private boolean lastOwned(String ID){
        return true;
    }
}
