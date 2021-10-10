/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

/**
 *
 * @author brenn
 */
public class mapUpdater {
    /*
    public static void newUnitState(Map gameState,UnitTypes newUnit){
        gameState.addDeployedForces(newUnit.getUxPos(), newUnit.getUyPos(), newUnit);
    }
    */
    public static void updateUnitState(Map gameState,UnitTypes removedUnit, UnitTypes addedUnit){ 
        gameState.removeUnitTypeinDeployedForces(removedUnit.uxPos,removedUnit.uyPos,removedUnit);//WARNING this isn't doing it's job right, it's probably not able to compare objects like it thinks
        gameState.addDeployedForces(addedUnit.getUxPos(), addedUnit.getUyPos(), addedUnit);
    }
}
