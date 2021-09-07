/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Units.pathing.AbstractUnitMovement;
import Units.pathing.Routing;
import Units.pathing.TerrainRules;

/**
 *
 * @author brenn
 */
//TODO Ideally this and it's subclasses should have their own package
public abstract class UnitTypes {//no constructor since init is actually done in the front end and this is called recursively
    
    transient AbstractUnitMovement moveHandler;
    
    
    TerrainRules terrainRules;
    
    String UnitType;
    String OWNER; //javascript tells the javascript which player it is so that the player can tag it
    int uhealth ,
        uspeed,
        urange ,//This may need to become a Range.class object but that relies on the javaScript so I'd rather not
            //Another option is to create a method that is called in createUnitCommand to instantiate range easily one time. (limits effects though so it's not a great solution
        udamage ,
        uxPos ,
        uyPos ,
        uzPos;
    
/////////Command Support Methods///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*
    Called to check if a unit is able to act, implementations apply their own logic and perform tasks related to that unit's 
    functionality
    */
    abstract public void prepare(GodsofWargame gameState);
    abstract public void removeUnit(GodsofWargame GameState);//allows the unit to handle specifics about it's removal
    abstract public void move(GodsofWargame gameState);
    abstract public void attack(GodsofWargame gameState);
    abstract public String testValue();
    abstract public void createSelf(GodsofWargame gameState, String ID);
////////Super Methods/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    @Override
    public String toString(){
        String unitInfo = "badSpots: " + terrainRules.toString() +" Owner:"+ getOWNER() + " Unit Type:" + getUnitType()+ " Health:" + getUhealth() + " Speed:" +  getUspeed() + " Range:" + getUrange() + " Damage:" + getUdamage() + " X,Y: " + getUxPos() + "," + getUyPos();
        return unitInfo;
    }
////////GETTERS & SETTERS/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public TerrainRules getTerrainRules() {
        return terrainRules;
    }

    public AbstractUnitMovement getMoveHandler() {
        return moveHandler;
    }
    
    public String getOWNER() {
        return OWNER;
    }

    public String getUnitType() {
        return UnitType;
    }

    public void setUnitType(String UnitType) {
        this.UnitType = UnitType;
    }
   
    public void setOWNER(String OWNER) {
        this.OWNER = OWNER;
    }

    public int getUhealth() {
        return uhealth;
    }

    public void setUhealth(int uhealth) {
        this.uhealth = uhealth;
    }

    public int getUspeed() {
        return uspeed;
    }

    public void setUspeed(int uspeed) {
        this.uspeed = uspeed;
    }

    public int getUrange() {
        return urange;
    }

    public void setUrange(int urange) {
        this.urange = urange;
    }

    public int getUdamage() {
        return udamage;
    }

    public void setUdamage(int udamage) {
        this.udamage = udamage;
    }

    public int getUxPos() {
        return uxPos;
    }

    public void setUxPos(int uxPos) {
        this.uxPos = uxPos;
    }

    public int getUyPos() {
        return uyPos;
    }

    public void setUyPos(int uyPos) {
        this.uyPos = uyPos;
    }

    public int getUzPos() {
        return uzPos;
    }

    public void setUzPos(int uzPos) {
        this.uzPos = uzPos;
    }
    public void changeUhealth(int damage) {
        this.uhealth -= damage;
    }
    
}
