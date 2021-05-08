/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Units.AbstractUnitAttack;
import Units.pathing.AbstractUnitMovement;
import java.util.HashMap;

/**
 *
 * @author brenn
 */
//NOTE This command is for finalizing a command Unit it doesn't actually execute the code needed for a command Unit to exist in gameState
//that is handled by createSelf() inside the command Unit which is executed by this command
public class createUnitCommandStructureCommand implements commandInterface{
    AbstractUnitAttack attackHandler;
    AbstractUnitMovement moveHandler;
    private UnitCommandStructure newUnit;
    HashMap<String, jsonsendHolder> dataForSending = new HashMap<>();
    @Override
    public void execute(GodsofWargame gameState,String Id){
        try{
        newUnit.setAttackHandler(attackHandler);
        newUnit.setMoveHandler(moveHandler);
        newUnit.createSelf(gameState,Id);
        } catch (ArrayIndexOutOfBoundsException E){
            System.out.println("Index error at createCommandStructure");
        }
        //distributionRuleSet(gameState.getMapState(),newUnit.createSelf(gameState,Id));
        //return dataForSending;
    }
    /*
    private void distributionRuleSet(Map gameState, jsonsendHolder serverSet){//Heres where fog of war rules get made
        Set<String> keys = gameState.getPlayers().keySet();
        for( String s : keys){ //Since there are no special rules on creation yet this for loop is fine
            
            dataForSending.put(s, serverSet);
        }
    }
    */
    @Override
    public void setUnit(UnitTypes unit){
        newUnit = (UnitCommandStructure) unit;
    }
    @Override
    public String testValue(){
        return "I'm currently a create CommandStructure command";
    }
}
