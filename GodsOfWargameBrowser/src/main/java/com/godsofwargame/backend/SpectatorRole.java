/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import com.godsofwargame.commands.commandInterface;

/** A role that defines the client can be a session on the server for receiving 
 * data but they can't send requests so any received commands and requests are ignored
 *
 * @author brenn
 */
public class SpectatorRole implements Role {
    public final String roleType = "SpectatorRole";
    transient GodsofWargame gameState;
    public SpectatorRole(GodsofWargame gameState){
        this.gameState = gameState;
    }
    @Override
    public boolean validateEgress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean validateIngress(commandInterface incomingCommand) {
        return false;
    }
    @Override
    public String getRoleType() {
       return roleType;
    }
    
    
}
