/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import com.godsofwargame.commands.commandInterface;
import Units.pathing.Routing;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypes;
import java.util.HashMap;
/**
 *
 * @author brenn
 */
public class moveUnitCommand implements commandInterface{//a small class that handles movement related commands

////COMMAND REQUIREMENTS//////////////////////////////////////////////////////////////////////////    
    
    private Routing pathingRoute;
    private UnitTypes movingUnit;
    
    @Override
    public void execute(GodsofWargame gameState, String ID){
        try {
        System.out.println("MoveUnitCommand executed");
        
        movingUnit.move(gameState, pathingRoute, ID);
        } catch (ArrayIndexOutOfBoundsException E){
            System.out.println("Index error at MoveUnit");
        }
    }
    
    @Override
    public void setUnit(UnitTypes unit){
        movingUnit = unit;
    }

    public Routing getPathingRoute() {
        return pathingRoute;
    }

    public UnitTypes getMovingUnit() {
        return movingUnit;
    }

    public void setPathingRoute(Routing pathingRoute) {
        this.pathingRoute = pathingRoute;
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
