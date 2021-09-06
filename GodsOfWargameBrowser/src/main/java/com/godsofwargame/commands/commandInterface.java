/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypes;

/**
 *
 * @author brenn
 * 
 */
//@JsonAdapter(TypeAdaptorCommand.class)
public interface commandInterface {
    
    //HashMap<String, jsonsendHolder> dataForSending = new HashMap<>();
    public void execute(GodsofWargame gameState,String Id);
    //public HashMap<String, jsonsendHolder> getJsonsendHolders();
    public void setUnit(UnitTypes unit);
    public String testValue();
}
