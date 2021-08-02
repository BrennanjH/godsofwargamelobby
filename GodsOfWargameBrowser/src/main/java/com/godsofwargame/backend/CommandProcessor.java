/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import com.godsofwargame.commands.commandInterface;

/**
 *
 * @author brenn
 */

public class CommandProcessor{ 
    public void processor (commandInterface command, GodsofWargame gameState, String Id){
        command.execute(gameState , Id);
    }
}