/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import JSONOrienter.TypeAdaptorRouting;
import Units.pathing.Routing;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypes;
import com.google.gson.annotations.JsonAdapter;
/**
 *
 * @author brenn
 */
public class moveUnitCommand implements commandInterface{//a small class that handles movement related commands

////COMMAND REQUIREMENTS//////////////////////////////////////////////////////////////////////////   
    
    @JsonAdapter(TypeAdaptorRouting.class)
    private Routing pathingRoute;
    
    //int[][] pathingRoute;
    private UnitTypes movingUnit;
    
    transient GodsofWargame gameState;
    @Override
    public void execute(GodsofWargame gameState, String ID){
        
        this.gameState = gameState;
        
        //Check if the unit's x and y coords can be safely Searched
        if(gameState.getMapState().getRow() > movingUnit.getUxPos() && gameState.getMapState().getCol() > movingUnit.getUyPos()){
            //Check if the unit's z coords can be safely searched
            if(gameState.getMapState().getDeployedForces()[movingUnit.getUxPos()][movingUnit.getUyPos()].size() > movingUnit.getUzPos()){
                UnitTypes serverSide = gameState.getMapState()
                                                .getUnitTypeinDeployedForces(movingUnit.getUxPos(), movingUnit.getUyPos(), movingUnit.getUzPos());
                if (validate(serverSide, ID)) {
                    System.out.println("moveUnitCommand: Validation Passed");
                    //Add pathingRoute to serverSideUnits moveHandler
                    serverSide.getMoveHandler().setPath(pathingRoute);

                } else {
                    System.out.println("moveUnitCommand: Validation Failed");
                }
            }
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
    private boolean validate(UnitTypes serverSide, String ID) {
        
        return compareUnits(serverSide, movingUnit) && checkOwner(serverSide, ID);
            
        
    }
    private boolean compareUnits(UnitTypes serverSide, UnitTypes clientSide){
        
        return serverSide.getUxPos() == clientSide.getUxPos() &&
               serverSide.getUyPos() == clientSide.getUyPos() &&
               serverSide.getUzPos() == 0 &&
               serverSide.getUzPos() == clientSide.getUzPos();
    }
    private boolean checkOwner(UnitTypes serverSide , String ID){
        System.out.println("Serverside Objects Owner: " + serverSide.getOWNER());
        System.out.println("command issuers ID: " +  ID);
        return ID.equals(serverSide.getOWNER());
        
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
