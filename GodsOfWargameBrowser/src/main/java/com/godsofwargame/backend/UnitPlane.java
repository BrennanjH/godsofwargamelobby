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
import Units.UnitPlaneAttack;
import Units.UnitPlaneCreate;
import Units.pathing.UnitPlaneMovement;
import Units.UnitTankRemoval;
import Units.pathing.Routing;
import Units.pathing.UnitTankTerrainRules;

/**
 *
 * @author brenn
 */
public class UnitPlane extends UnitTypes{
    @Override
    public void move(GodsofWargame gameState){//TODO finish this, also int newZ is probably useless
        moveHandler = new UnitPlaneMovement(this);
        moveHandler.move(gameState);
    }
    @Override
    public void attack(GodsofWargame gameState){
        AbstractUnitAttack attackHandler = new UnitPlaneAttack(this, gameState);
        attackHandler.attack(gameState);
    }
    @Override
    public void removeUnit(GodsofWargame gameState){
        //They die the same way so unless planes change how they die this works great
        AbstractUnitRemoval removalHandler= new UnitTankRemoval(this);
        removalHandler.removeUnit(gameState);
    }
    @Override
    public void createSelf(GodsofWargame mapState,String ID){
        AbstractUnitCreate createHandler = new UnitPlaneCreate(this);
        createHandler.create(mapState,ID);
    }
    @Override
    public String testValue(){
        return "I'm a plane brrrrrr";
    }
    private int bottomStacker(Map gameState){//returns size of ArrayList at the given x,y
        return gameState.getDeployedForces()[this.uxPos][this.uyPos].size();
    }
    @Override
    public void prepare(GodsofWargame gameState){
        //For now it assumes that terrainTypes aren't sent
        terrainRules = new UnitTankTerrainRules();
    }
    
}
