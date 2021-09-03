/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;


import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.commands.commandInterface;
import java.util.HashMap;

/**
 *
 * @author brenn
 */
public class createUnitCommand implements commandInterface {
    //HashMap<String, jsonsendHolder> dataForSending = new HashMap<>();
    private UnitTypes newUnit;//this only works if I set it to be a spec unit type
    
    
//********GETTERS AND SETTERS*********************************************************************************************************************
    @Override
    public String testValue(){
        return "I'm currently a create Unit command";
    }
    @Override
    public void setUnit(UnitTypes unit){
        newUnit = unit;
    }
//********KICKSTART*********************************************************************************************************************

    @Override 
    public void execute(GodsofWargame gameState,String Id){
        try{
        System.out.println("Execute in create Unit called");
        System.out.println(newUnit.toString());
        
        newUnit.createSelf(gameState, Id);
        } catch (ArrayIndexOutOfBoundsException E){
            System.out.println("Index error at createUnit");
        }
        //distributionRuleSet(gameState.getMapState(),newUnit.createSelf(gameState,Id));
        //return dataForSending;
        
    }
//********HELPER METHODS*********************************************************************************************************************
    
    private boolean isTerrainValid(Map gameState){
        if( gameState.getTerrain(newUnit.getUxPos(),newUnit.getUyPos()).getType().equals("MOUNTAIN") ){//WARNING originally was y,x but I'm trying to fix code to all be x,y so I've changed this

            System.out.println("isTerrainvalid returning false, create command failed");
            
            return false;
        }
        else
        return true;
    }
    private int bottomStacker(Map gameState){//returns size of ArrayList at the given x,y
        return gameState.getDeployedForces()[newUnit.getUxPos()][newUnit.getUyPos()].size();
    }
    /*
    @Override
    public HashMap<String,jsonsendHolder> getJsonsendHolders(){
        return dataForSending;
    }
    */
    
}