/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import java.util.HashMap;
import java.util.Set;
/**
 *
 * @author brenn
 */
public class moveUnitCommand implements commandInterface{//a small class that handles movement related commands
    HashMap<String, jsonsendHolder> dataForSending = new HashMap<>();
    int newX;
    int newY;
    int newZ;
    UnitTypes movingUnit;
    //jsonsendHolder listHolder;
    
    @Override
    public void execute(GodsofWargame gameState, String ID){
        try {
        System.out.println("MoveUnitCommand executed");
        //listHolder = movingUnit.move(gameState,newX,newY,newZ);
        movingUnit.move(gameState,newX,newY, ID);
        } catch (ArrayIndexOutOfBoundsException E){
            System.out.println("Index error at MoveUnit");
        }
        //distributionRuleSet(gameState.getMapState(),movingUnit.move(gameState,newX,newY, ID));
        //return dataForSending;
    }
    /*
    private void distributionRuleSet(Map gameState, jsonsendHolder serverSet){//Heres where fog of war rules get made
        Set<String> keys = gameState.getPlayers().keySet();
        for( String s : keys){ //Since there are no special rules on movement yet this for loop is fine
            dataForSending.put(s, serverSet);
        }
    }
    */
    @Override
    public void setUnit(UnitTypes unit){//literally useless as reflection handles setting information.
        movingUnit = unit;
    }
    /*
    @Override
    public HashMap<String,jsonsendHolder> getJsonsendHolders(){
        return dataForSending;
    }
    */
    @Override
    public String testValue(){
        return "I'm currently a create Move command";
    }
}
