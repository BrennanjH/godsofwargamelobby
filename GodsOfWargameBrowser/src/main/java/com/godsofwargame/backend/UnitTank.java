/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Units.AbstractUnitRemoval;
import Units.UnitTankAttack;
import Units.UnitTankCreate;
import Units.pathing.UnitTankMovement;
import Units.UnitTankRemoval;

/**
 *
 * @author brenn
 */
public class UnitTank extends UnitTypes {
    @Override
    public void move(GodsofWargame gameState,int newX,int newY, String ID){ //final parameter not needed
        moveHandler = new UnitTankMovement(this);//See package Units for code
        moveHandler.move(gameState,newX,newY,ID);
    }
    @Override
    public void attack(GodsofWargame gameState){
        UnitTankAttack attackHandler = new UnitTankAttack(this, gameState);
        attackHandler.attack(gameState);
    }
    @Override
    public void removeUnit(GodsofWargame gameState){
        AbstractUnitRemoval removalHandler= new UnitTankRemoval(this);
        removalHandler.removeUnit(gameState);
    }
    @Override
    public void createSelf(GodsofWargame gameState,String ID){
        UnitTankCreate creationHandler = new UnitTankCreate(this);
        creationHandler.create(gameState, ID);
    }
    @Override
    public String testValue(){
        return "I'm definitly a UnitTank type";
    }
    
}
