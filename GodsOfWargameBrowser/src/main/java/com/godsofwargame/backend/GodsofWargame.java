/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;


import Faction.Member;
import Faction.NoTeamAssociationException;
import Faction.Team;
import TimerSystems.TimerScheduler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Timer;
import java.util.TimerTask;

import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
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
    //@Autowired
    @Autowired
    public Map mapState;// = new Map();
    private Timer attackTimer;
    private TimerTask task;
    private HashMap<String, ArrayList<UnitCommandStructure>> commanders = new HashMap<>();//Links player Id to all owned CommandUnits
    @Autowired
    private MatchProperties properties;
    @Autowired
    private LoadState readyStates;
    private List<Team> factions;
    
    public MatchProperties getProperties() {
        return properties;
    }
    
    public Map getMapState() {
        return mapState;
    }
    public HashMap<String, Session> getClients(){
        return clients;
    }
    public void cancelTimer(){
        attackTimer.cancel();
    }

    public LoadState getReadyStates() {
        return readyStates;
    }

    public void resetReadyState(){
        readyStates = new LoadState();
    }
    
    protected void preload(){//Sets up the server and lobby based on gameProperties
        //Create Terrain
        mapState.GenerateTerrain();//gets terrain placed into 2d array stored in map object
        //Set up fields
        factions = new ArrayList<>();
        commanders = new HashMap<>();
        //Change preload flag to true
        readyStates.setPreLoad(true);
    }
    public void load(){//kicks off a chain that generates the terrain of the match (predetermined or not)
        System.out.println("GodsofWargame: load: load initiated");
        //start Timers
        attackTimer = new Timer();
        task = new TimerScheduler( this);
        attackTimer.schedule(task,1000,5000);
        //change fullyLoaded flag to true
        readyStates.setFullyLoaded(true);
    } 

    public HashMap<String, ArrayList<UnitCommandStructure>> getCommanders() {
        return commanders;
    }

    public void addCommander(UnitCommandStructure command){
        if(commanders.containsKey(command.getOWNER())){
            commanders.get(command.getOWNER()).add(command);
        } else {
            ArrayList<UnitCommandStructure> temp = new ArrayList<>();
            temp.add(command);
            commanders.put(command.getOWNER(), temp);
        }
        
    }
    public void removeCommander(UnitCommandStructure command){
        ArrayList<UnitCommandStructure> temp = commanders.get(command.getOWNER());
        //int index;
        for (int i =0; i< temp.size(); i++){
            if (temp.get(i).equals(command)){
                System.out.println("removeCommander: in if remove unit");
                temp.remove(i);
                break;
                
            }
        }
        if (commanders.get(command.getOWNER()).isEmpty()){
            System.out.println("removeCommander: in if remove player");
            commanders.remove(command.getOWNER());
        }
    }
    //Must be called at session send so that all information pertaining to player is removed
    public void removeSession(Session leaver) throws IOException{
        String ID = leaver.getId();
        commanders.remove(ID);
        clients.remove(ID);
        removeMemberFromTeam(ID);
        mapState.removeSession(leaver);//Not sure if I want unit's to call their death rules but If yes then commanders.remove can be removed since the map will remove the command units internally
        
    }
    public void removeMemberFromTeam(String memberID){
        List<Member> removeList = new ArrayList<>();
        System.out.println("member ID: " + memberID);
        for(Team t : factions){
            for (Member m : t.getTeam()){
                if (m.getId().equals(memberID)){
                    removeList.add(m);
                    
                }
            }
            for(int i =0; i < removeList.size(); i++){
                t.getTeam().remove(removeList.get(i));
            }
            
        }
        
    }
    public List<Team> getFactions() {
        return factions;
    }

    
    private boolean compareUnits(UnitTypes unit1, UnitTypes unit2){
        return (unit1.uxPos == unit2.uxPos) && (unit1.uyPos == unit2.uyPos) && (unit1.uzPos == unit2.uzPos);
    }
    //Finds the team that a player belongs to, Might need to return list if logic is needed in the future
    public Team returnPlayerTeam(Member teamMember ) throws NoTeamAssociationException {
        for ( Team t : factions){
            for (Member m : t.getTeam()){
                if(m.equals(teamMember)){
                    return t;
                }
            }
        }
        throw new NoTeamAssociationException();
    }
    public void  resetGameStateRetainPlayers(){
        cancelTimer();
        mapState.resetMapStateRetainPlayers();
        resetReadyState();
        preload();
    }
}
