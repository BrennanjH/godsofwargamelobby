/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;


import TimerSystems.TimerScheduler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

import javax.websocket.Session;
/*
import java.net.ServerSocket; Probably won't be used
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
*/
//CLASS that instantiates the game 
//TODO change all Map.class calls to GodsofWargame.class calls so that all gameState objects can be referenced from one class
public class GodsofWargame {
   
    
    protected static HashMap<String, Session> clients = new HashMap<>();//STATIC
    public Map mapState = new Map();
    private Timer attackTimer;
    private TimerTask task;
    private HashMap<String, UnitCommandStructure> commanders = new HashMap<>();//May need to be either a HashMap or Set
    
    private boolean isInstantiated = false;
    
    public Map getMapState() {
        return mapState;
    }
    public HashMap<String, Session> getClients(){
        return clients;
    }
    public void cancelTimer(){
        attackTimer.cancel();
    }
  
    public boolean checkState(){ //looks to see if a map class already has been instantiated 
       
       return isInstantiated;
    }
    
    protected void load(){//kicks off a chain that generates the terrain of the match (predetermined or not)
       attackTimer = new Timer();
       task = new TimerScheduler( this);
       mapState.GenerateTerrain();//gets terrain placed into 2d array stored in map object
       attackTimer.schedule(task,1000,5000);//TODO Figure out why this can't be stopped and started
       //game.getCurrent();
       isInstantiated = true;
    } 

    public HashMap<String, UnitCommandStructure> getCommanders() {
        return commanders;
    }

    public void addCommander(UnitCommandStructure command){
        commanders.put(command.getOWNER(), command);
    }
    public void removeCommander(UnitCommandStructure command){
        commanders.remove(command.getOWNER());
    }
    //Must be called at session send so that all information pertaining to player is removed
    public void removeSession(Session leaver){
        String ID = leaver.getId();
        commanders.remove(ID);
        clients.remove(ID);
        mapState.removeSession(leaver);//Not sure if I want unit's to call their death rules but If yes then commanders.remove can be removed since the map will remove the command units internally
        
    }

    public void setIsInstantiated(boolean isInstantiated) {
        this.isInstantiated = isInstantiated;
    }
    
}
