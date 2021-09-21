/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Location.Terrain;
import Location.Territory;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *  The purpose of this class to take the gameState and use it to split the data
 * into data appropriate for each specific User, This is separate from how CommandObjects
 * handle data but fulfills the same purpose.
 * @author brenn
 */
public class peerSpecificIdentifier { //TODO rename this class to something more reasonable
    //A method that will return sorted information on terrain and units, Best used to refresh the game
    //TODO have this method only send messages to the person who joined.
    public static HashMap<String, jsonsendHolder> sortData(GodsofWargame gameState){//Public class that any class can use to sort data between clients
            HashMap<String, jsonsendHolder> dataForSending = new HashMap<>();//the sorted data's goes here
            
            jsonsendHolder empty = new jsonsendHolder(gameState.getProperties());
            
            dataForSending = setClients(dataForSending, gameState,empty);
            dataForSending = seperateUnitsByUser( getUnitsAsList(gameState), dataForSending);
            dataForSending = seperateTerrainByUser( getTerrainAsList(gameState), dataForSending);//TODO fix unitLoss bug (fixed? don't remember this bug)
            dataForSending = seperatePlayerDataByUser( gameState.getMapState().getPlayers(), dataForSending); //no need for a list since 1:1 ratio of playerData to players
            //dataForSending = seperateTerritoriesByUser(gameState.getMapState().getTerritories(), dataForSending);
            return dataForSending;
    }
    //If terrain ever has a special behavior (such as fog of war) this overload can be removed
    //sortData except it assumes a state change rather than a state refresh
    public static HashMap<String, jsonsendHolder> sortData(GodsofWargame gameState,jsonsendHolder data ){
        HashMap<String, jsonsendHolder> dataForSending = new HashMap<>();
        dataForSending = setClients(dataForSending, gameState,data);
        dataForSending = seperateUnitsByUser( getUnitsAsList(gameState), dataForSending);
        dataForSending = seperatePlayerDataByUser( gameState.getMapState().getPlayers(), dataForSending);
        return dataForSending;
    }
    
    public static HashMap<String, jsonsendHolder> simpleData(GodsofWargame gameState,jsonsendHolder data){
        HashMap<String, jsonsendHolder> dataForSending = new HashMap<>();
        dataForSending = setClients(dataForSending, gameState,data);
        return dataForSending;
    }
    /*
    private static HashMap<String, jsonsendHolder> setClients(HashMap<String,jsonsendHolder> sortedData, GodsofWargame gameState){
        jsonsendHolder hold;
        for (String s : gameState.getClients().keySet()){
            hold = new jsonsendHolder();
            sortedData.put(s, hold);
        }
        return sortedData;
    }
    */
    public static HashMap<String,jsonsendHolder> seperateTerritoriesByUser(List<Territory> territories, HashMap<String,jsonsendHolder> sortedData){
        for (String s : sortedData.keySet()){
            
            sortedData.get(s).setTerritories(territories);
        }
        return sortedData;
        
        
        //return sortedData;
    }
    //fills HashMap with unique reference jsonsendHolders
    private static HashMap<String, jsonsendHolder> setClients(HashMap<String,jsonsendHolder> sortedData, GodsofWargame gameState,jsonsendHolder data){
        jsonsendHolder hold;//WARNING I'm almost certain that each hold put inside sortedData will be the same reference
        for (String s : gameState.getClients().keySet()){
            hold = new jsonsendHolder(data);
            sortedData.put(s, hold);
        }
        return sortedData;
    }
    /////Terrain Holders/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<Terrain> getTerrainAsList (GodsofWargame gameState){
        List<Terrain> temp = new ArrayList<>();
        for(int i =0; i<gameState.getMapState().getRow();i++){
            for(int j =0; j<gameState.getMapState().getCol();j++){
                temp.add(gameState.getMapState().getTerrain(i,j));
            }
        }
        return temp;
    }
    private static HashMap<String,jsonsendHolder> seperateTerrainByUser(List<Terrain> terrainList,HashMap<String,jsonsendHolder> sortedData){
        for (String s : sortedData.keySet()){
            
            for(Terrain t : terrainList){//WARNING This seems like an unnecessary step
                sortedData.get(s).addTerrain(t);
            }
            //TODO pass Terrain to a fog of war method to check if terrain is visible by player if fog of war is created
            
        }
        return sortedData;
    }
    //////////Unit Handlers////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static List<UnitTypes> getUnitsAsList(GodsofWargame gameState){//Transforms unitList in Map into one List for serialization
        List<UnitTypes> temp = new ArrayList<>();
        for(int i =0; i<gameState.getMapState().getRow() ;i++){
            for(int j =0; j<gameState.getMapState().getCol() ;j++) {
                for(int k =0; k<gameState.getMapState().getDeployedForces()[i][j].size() ;k++){
                    
                    temp.add(gameState.getMapState().getUnitTypeinDeployedForces(i,j,k));
                    //System.out.println(gameState.getMapState().getUnitTypeinDeployedForces(i,j,k).toString());
                }
            }
        }
        return temp;
    }
    /*
        returns the HashMap of jsonsendHolders of all the units, since no fog of war exists no rules are put here but 
        in the future that will be necessary if such a feature is implemented
    */
    private static HashMap<String,jsonsendHolder> seperateUnitsByUser(List<UnitTypes> unitList ,HashMap<String,jsonsendHolder> sortedData){
        for (String s : sortedData.keySet() ) {
            for(UnitTypes u : unitList){
                sortedData.get(s).addUnit(u);
                //TODO pass unit to a fog of war method to check if Unit is visible by player if fog of war is created
            }
        }
        return sortedData;
    }
    private static HashMap<String,jsonsendHolder> seperatePlayerDataByUser(HashMap<String, PlayerData> playerList ,HashMap<String,jsonsendHolder> sortedData){
        for (String s : sortedData.keySet() ) {
            //s is used to get the jsonsendHolder and unlike Terrain or Units playerData is on a 1 : 1 basis with players so creating     
            sortedData.get(s).setPlayerInfo(playerList.get(s));
                
        }
        return sortedData;
    }
}
