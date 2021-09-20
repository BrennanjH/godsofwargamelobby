/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;
import Location.Terrain;
import Location.Territory;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import javax.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Map  {//acts as the gamestate object
    
    //@Value("${map.row}")
    int row;// = Integer.parseInt(stringRow);
    

    //@Value("${map.column}")
    int col;// = Integer.parseInt(stringCol);
    
    private Terrain[][] terrainGrid;// = new Terrain[row][col]; //holds The current state of the grids Terrain
    
    //Should be removed from Map
    HashMap<String, PlayerData> players;// = new HashMap<>(); 
    
    //ArrayList<playerData> players = new ArrayList<>();
    
    private List<UnitTypes>[][] deployedForces;// = new List[row][col];
    private List<Territory> territories;// = new ArrayList<>();

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Autowired
    public Map(@Value("${map.row:10}") String rows,@Value("${map.column:10}") String cols){
        System.out.println("Map constructor: rows: " + rows);
        row = Integer.parseInt(rows);
        col = Integer.parseInt(cols);
        System.out.println("Map constructor: rows as int: " + row);
        terrainGrid = new Terrain[row][col];
        players = new HashMap<>();
        deployedForces = new List[row][col];
        territories = new ArrayList<>();
        
        for(int i =0;i<deployedForces.length;i++){
            
            for(int j =0;j<deployedForces[i].length;j++){
                deployedForces[i][j] = new ArrayList<>();
            }
        }
        System.out.println("Map instantiated");
    }

    public List<UnitTypes>[][] getDeployedForces() {
        return deployedForces;
        
    }
    
    /*
    public List<UnitTypes> getListInDeployedForces(int x ,int y){
        return deployedForces[x][y];
    }
    */
    public void addDeployedForces(int x,int y,UnitTypes adding) {//TODO figure out how to handle adding a unit vrs setting a unit
        this.deployedForces[x][y].add(adding);//z cord shouldn't matter if using this method
    }
    public void removeUnitTypeinDeployedForces(int x,int y,UnitTypes delete){
        this.deployedForces[x][y].remove(delete);
    }
    private void removeUnitTypeAtCoord(int x,int y,int z){//Should never be used, since z coords aren't checked allows cheating
        //create Z cord checker method if this type of removal is determined to be necessary
        this.deployedForces[x][y].remove(z);
    }
    public UnitTypes getUnitTypeinDeployedForces(int x,int y,int z) {
        
        return deployedForces[x][y].get(z);
    }
   //WARNING y,x bug either a fillTerrain.js problem, JSONsendHolder problem, or a generate 2d, Terrain Array problem.
    public void GenerateTerrain(){ //called by load to generate terrain for map into 2d integer array
     //Terrain terrain = new Terrain();
        Terrain temp;
        for (int rows=0; rows<terrainGrid.length;rows++){
            for (int cols=0; cols<terrainGrid[rows].length;cols++){
                temp = new Terrain(rows,cols);
                terrainGrid[rows][cols]= temp;
                //System.out.println(terrainGrid[rows][cols].getType());
            }
        }
        //printTerrain();
        
   }
    
   public void printTerrain(){
       for (int rows=0; rows<terrainGrid.length;rows++){
            for (int cols=0; cols<terrainGrid[rows].length;cols++){
                //temp = new Terrain();
                //terrainGrid[rows][cols]= temp.generate();
                System.out.println(terrainGrid[rows][cols].getType());
            }
        }
   }
    
   public int getRow(){
      return row;
   }
   public int getCol(){
      return col;
   }
   public Terrain getTerrain(int x, int y){
      //System.out.println(terrainGrid[x][y].getType());
       return terrainGrid[x][y];
      
   }
   
   public List<Territory> getTerritories() {
        return territories;
    }

    public void addPlayer(String key, PlayerData newPlayer){
        players.put(key, newPlayer);
    }
    private void removePlayer(String lostPlayerID){//private to protect from misuse
        players.remove(lostPlayerID);
    }
    public PlayerData getPlayer(String playersID){
        return players.get(playersID);
    }

    public HashMap<String, PlayerData> getPlayers() {
        return players;
    }
    public void removeSession(Session leaver){
        removePlayer(leaver.getId());
        removeOwnerFromDeployedForces(leaver.getId());
    }
    private void removeOwnerFromDeployedForces(String ID){
        for (int i=0; i < getRow();i++){
            for (int j =0;j<getCol();j++){
                
                
                //To improve For Loop create on run through tag the units to be removed,after loop remove them, then update them
                //System.out.println("Map: removeOwnerFromDeployedForces: deployedForces size: " + getDeployedForces()[i][j].size());
                for(int k =0; k < getDeployedForces()[i][j].size(); k++){
                    
                    if(ID.equals(getUnitTypeinDeployedForces(i,j,k).OWNER ) ){
                        removeUnitTypeinDeployedForces(i,j,getUnitTypeinDeployedForces(i,j,k));
                        k--;
                        
                    }
                    
                }
                
                updateZPos(i,j);
            }
        }
        
    }
    private void updateZPos(int x, int y){
        //System.out.println(gameState.getMapState().getDeployedForces()[x][y].size());
        for(int i=0;i<getDeployedForces()[x][y].size();i++){
            UnitTypes hold =  getUnitTypeinDeployedForces(x,y,i);
            hold.setUzPos(i);
        }
    }
}
