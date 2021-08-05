/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Location.Terrain;
import Location.Territory;
import java.util.ArrayList;
import java.util.List;
/**A class that represents all the data that is going to be serialized for the Frontend
 *
 * @author brenn
 */
public class jsonsendHolder {
    private List<Terrain> terrainList;
    private List<UnitTypes> unitList;
    private playerData playerInfo;
    private List<Territory> territories;

    
    private String serverMes;//is this truely needed?
    private firstResponseSetup firstResponseData;

    public jsonsendHolder() {
        terrainList = new ArrayList<>();
        unitList = new ArrayList<>();
        //deleteUnitList = new ArrayList<>();
        firstResponseData = new firstResponseSetup();
    }
    //creates a new references for lists but doesn't change the objects in the lists
    public jsonsendHolder( jsonsendHolder holder) {
        this.terrainList = new ArrayList(holder.getTerrainList());
        this.unitList = new ArrayList(holder.getUnitList());
        //this.deleteUnitList = new ArrayList(holder.getDeleteUnitList());
        this.serverMes = holder.getServerMes();
        this.firstResponseData = holder.getFirstResponseData();
    }
    
    
    public void addUnit(UnitTypes unit){
        unitList.add(unit);
    }
    public void addTerrain(Terrain terrain){
        terrainList.add(terrain);
    }
    /*
    public void addUnitForDeletion(UnitTypes unit){
        deleteUnitList.add(unit);
    }
    */
    public playerData getPlayerInfo() {
        return playerInfo;
    }
    
    public void setPlayerInfo(playerData playerInfo) {
        this.playerInfo = playerInfo;
    }
    public List<Territory> getTerritories() {
        return territories;
    }

    public void setTerritories(List<Territory> territories) {
        this.territories = territories;
    }
    public void addServerMessage(String message){
        serverMes = message;
    }

    public void setTerrainList(List<Terrain> terrainList) {
        this.terrainList = terrainList;
    }

    public void setUnitList(List<UnitTypes> unitList) {
        this.unitList = unitList;
    }
    /*
    public  void setDeleteUnitList(List<UnitTypes> deleteUnitList) {
        this.deleteUnitList = deleteUnitList;
    }
    */
    public void setFirstResponseData(firstResponseSetup firstResponseData) {
        this.firstResponseData = firstResponseData;
    }

    public  List<Terrain> getTerrainList() {
        return terrainList;
    }

    public  List<UnitTypes> getUnitList() {
        return unitList;
    }
    /*
    public  List<UnitTypes> getDeleteUnitList() {
        return deleteUnitList;
    }
    */

    public  String getServerMes() {
        return serverMes;
    }

    public  firstResponseSetup getFirstResponseData() {
        return firstResponseData;
    }

    
    
    
    
    
}
