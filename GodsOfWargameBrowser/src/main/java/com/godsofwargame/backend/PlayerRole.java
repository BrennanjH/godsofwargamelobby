/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import com.godsofwargame.commands.commandInterface;

/** A role that defines that player can interact with the server, It is essentially
 * a role that is one level below admin
 *
 * @author brenn
 */
public class PlayerRole implements Role {
    public final String roleType = "PlayerRole";
    transient GodsofWargame gameState;
    public PlayerRole(GodsofWargame gameState){
        this.gameState = gameState;
    }
    

    @Override
    public boolean validateEgress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateIngress(commandInterface incomingCommands) {
        return true;
    }

    @Override
    public String getRoleType() {
       return roleType;
    }
    
}
