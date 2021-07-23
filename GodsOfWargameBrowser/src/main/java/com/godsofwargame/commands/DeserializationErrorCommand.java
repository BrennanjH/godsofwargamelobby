/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.backend.commandInterface;

/**
 *
 * @author brenn
 */
public class DeserializationErrorCommand implements commandInterface {

    @Override
    public void execute(GodsofWargame gameState, String Id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setUnit(UnitTypes unit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String testValue() {
        return "This is definitly a DeserializationErrorCommand";
    }
    
}
