/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Units.AbstractUnitRemoval;
import Units.UnitAntiAirAttack;
import Units.UnitTankCreate;
import Units.UnitTankRemoval;
import Units.pathing.UnitTankMovement;
import Units.pathing.UnitTankTerrainRules;

/**
 *
 * @author brenn
 */
public class UnitAntiAir extends UnitTypes {

    @Override
    public void prepare(GodsofWargame gameState) {
        moveHandler = new UnitTankMovement(this, gameState);
        terrainRules = new UnitTankTerrainRules();        
        property = "GROUND";
    }

    @Override
    public void removeUnit(GodsofWargame GameState) {
        AbstractUnitRemoval removalHandler= new UnitTankRemoval(this);
        removalHandler.removeUnit(GameState);
    }

    @Override
    public void move(GodsofWargame gameState) {
        moveHandler.move(gameState);
    }

    @Override
    public void attack(GodsofWargame gameState) {
        UnitAntiAirAttack attackHandler = new UnitAntiAirAttack(this, gameState);
        attackHandler.attack(gameState);
        //TODO create antiAir attack behavior
    }

    @Override
    public String testValue() {
        return "This is most definitly an AntiAir Unit";
    }

    @Override
    public void createSelf(GodsofWargame gameState, String ID) {
        UnitTankCreate creationHandler = new UnitTankCreate(this);
        creationHandler.create(gameState, ID);
    }
    
}
