/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Units.AbstractUnitAttack;
import Units.AbstractUnitCreate;
import Units.pathing.AbstractUnitMovement;
import Units.AbstractUnitRemoval;
import Units.UnitCommandStructureCreate;
import Units.UnitCommandStructureRemoval;
import Units.UnitTankAttack;
import Units.pathing.Routing;
import Units.pathing.UnitTankMovement;
import Units.pathing.UnitTankTerrainRules;


/** 
 *
 * @author brenn
 */
//If command structure is lost game ends. 
//TODO create new removeUnit types, Also command Unit's are special in that they aren't predifined in mechanics so hardcoding tank 
//movement is not exceptable for this class
public class UnitCommandStructure extends UnitTypes{
    //InterfaceUnitCreate createHandler; //To be filled with JSON 
    //AbstractUnitAttack attackHandler;
    //AbstractUnitMovement moveHandler;
    //InterfaceUnitRemoval removalHandler;
    
    @Override
    public void move(GodsofWargame gameState){
        moveHandler.move(gameState);
    }
    @Override
    public void attack(GodsofWargame gameState){
        UnitTankAttack attackHandler = new UnitTankAttack(this, gameState);
        attackHandler.attack(gameState);
    }
    
    /*
    This method removes this unit from the gameState.getMapState(). Removal of command structure changes player to spectator.
    */
    @Override
    public void removeUnit(GodsofWargame GameState){
        AbstractUnitRemoval removalHandler = new UnitCommandStructureRemoval(this);
        removalHandler.removeUnit(GameState);
    }
    @Override
    public void createSelf(GodsofWargame gameState,String ID){
        AbstractUnitCreate createHandler = new UnitCommandStructureCreate(this);
        createHandler.create(gameState, ID);
    }
    
    @Override
    public String testValue(){
        return "I'm definitly a UnitTank type";
    }


    public void setMoveHandler(AbstractUnitMovement moveHandler) {
        this.moveHandler = moveHandler;
    }
    /*
    A method that acts as a constructor initiating fields that may be necessary, It is called immediatly after parsing from JSON
    */
    @Override
    public void prepare(GodsofWargame gameState){
        //For now it assumes that terrainTypes aren't sent
        moveHandler = new UnitTankMovement(this, gameState);
        terrainRules = new UnitTankTerrainRules();
        property = "GROUND";
    }
    
}
