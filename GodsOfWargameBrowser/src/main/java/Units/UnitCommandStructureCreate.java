/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Faction.NoTeamAssociationException;
import Faction.Team;
import Location.ControlTerritory;
import Location.Coordinate;
import Location.HighLevelCoord;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitCommandStructure;
import com.godsofwargame.backend.mapUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brenn
 */
public class UnitCommandStructureCreate extends AbstractUnitCreate{
    UnitCommandStructure mover;//Since only commanders can use this create class UnitTypes isn't used here
    final int UCSPrice = 550;
    public UnitCommandStructureCreate(UnitCommandStructure comm){
        mover = comm;
    }
    @Override
    public void create(GodsofWargame gameState, String Id){
        //jsonsendHolder listHolder = new jsonsendHolder();
        if(isTerrainValid(gameState.getMapState()) && Id.equals(mover.getOWNER())){
            int cost = getCost();
            if(cost < gameState.getMapState().getPlayer(Id).getMoney() ) {
                
                    if(!(isHostileUCSNearby(gameState, 5))){
                        //Charge the players account
                        gameState.getMapState().getPlayer(Id).changeMoney(cost * -1);
                        mover.setOwnerMember(gameState.getMapState().getPlayer(Id).getPlayerMember());
                        //The new command Unit should be positioned on the map
                        bottomStacker(gameState.getMapState());
                        //Create a territory piece below the commander so that new units can be spawned in where the commander was spawned
                        Coordinate territoryLocation = new HighLevelCoord(mover.getUxPos(),mover.getUyPos());

                        try { 
                            //Unused as ControlTerritroy automatiaclly registers itself with mapstate
                            ControlTerritory newTerritory = new ControlTerritory(
                                    gameState.returnPlayerTeam(gameState.getMapState().getPlayer(mover.getOWNER()).getPlayerMember())
                                    , territoryLocation
                                    , gameState
                            );
                        } catch (NoTeamAssociationException ex) {
                            Logger.getLogger(UnitCommandStructureCreate.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //Add the commanders to gameState so future references are easier to get
                        gameState.addCommander(mover);//IMPORTANT LINE OF CODE FOR COMMANDERS

                        System.out.println("create Unit Command successful");
                    }
                
            }
        }
        
        //return listHolder;
    }
    private void bottomStacker(Map mapState){//places the unit at the bottom of the stack
        //System.out.println(gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size());
        mapState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].add(mover);
        mover.setUzPos(mapState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].indexOf(mover));
        //return mapState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size() -1;
    }
    private boolean isTerrainValid(Map gameState){
        return mover.getTerrainRules().isValid(gameState.getTerrain(mover.getUxPos(),mover.getUyPos()));
        
    }
    private int getCost(){
        return (mover.getUdamage()*damagePrice)
                + (mover.getUhealth()*healthPrice)
                + (mover.getUrange()*rangePrice)
                + (mover.getUspeed()*speedPrice)
                + UCSPrice;
    }
    /*
    //A method that uses teams to identify if a command unit is too close
    private boolean isHostileUCSNearby(GodsofWargame gameState,int radius) throws NoTeamAssociationException {
        //Get friendly team of object
        
        Team friendlyForces = gameState.returnPlayerTeam(mover.getOwnerMember());
        
        //Check if a hostile team has a command unit within the radius
        for(String player : gameState.getCommanders().keySet()){
            if(gameState.returnPlayerTeam(gameState.getMapState().getPlayer(player).getPlayerMember()).equals(friendlyForces)) {
                for(UnitCommandStructure UCS : gameState.getCommanders().get(player)){
                    if (  (Math.abs(UCS.getUxPos() - mover.getUxPos()) < radius)
                                && (Math.abs(UCS.getUyPos() - mover.getUyPos()) < radius) ){
                        return true;
                    }
                }
            }
            
                
        }
        return false;
    }
    */
    //Checks if a hostile command Unit exists within the radius given
    private boolean isHostileUCSNearby(GodsofWargame gameState,int radius){
        //loop through players that own a command Unit
        for(String player : gameState.getCommanders().keySet()){
            //Is player the same as the owner?
            if(!(mover.getOWNER().equals(player)) ) {
                //loop through the players command units 
                for(UnitCommandStructure UCS : gameState.getCommanders().get(player)){
                    //Is the player a teammember; This line of coded could also be done with team comparisions instead
                    if( gameState.getMapState().getPlayer(mover.getOWNER()).getPlayerMember().getFactionName()
                            .equals(gameState.getMapState().getPlayer(UCS.getOWNER()).getPlayerMember().getFactionName())) {
                        continue;
                    }
                    //Is UCS too close to mover
                    if (  (Math.abs(UCS.getUxPos() - mover.getUxPos()) <= radius)
                                && (Math.abs(UCS.getUyPos() - mover.getUyPos()) < radius) ){
                        return true;
                    }
                }
            }
            
                
        }
        return false;
    }
}
