/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import com.godsofwargame.commands.commandInterface;

/** An interface that defines how a server can interact with a player
 *
 * @author brenn
 */
public interface Role {
    //decides how information related to the game can be viewed by a player
    public boolean validateEgress();
    public String getRoleType();
    //Gets the player from the session Id and sends information to them correctly
    public boolean validateIngress(commandInterface incomingCommand);
    
    
}
