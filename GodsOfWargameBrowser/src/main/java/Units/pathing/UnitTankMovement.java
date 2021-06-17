/*
 * To change mover license header, choose License Headers in Project Properties.
 * To change mover template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.mapUpdater;

/**
 *
 * @author brenn
 */
public class UnitTankMovement extends AbstractUnitMovement{
    UnitTypes mover;
    //TerrainRules terrainRules;
    int lx,ly;
    Routing path;
    GodsofWargame gameState;
    //constructor to be used by new units that have tank pathing but not tank terrain interactions
    public UnitTankMovement(UnitTypes unit, GodsofWargame gameState) {
        mover = unit;
        this.gameState = gameState;
        //terrainRules = unit.getTerrainRules();
    }
    @Override
    public void beginMovement(){
        
    }
    //Generates Routing object
    @Override
    public void move(GodsofWargame gameState,int newX,int newY, String ID){
        
    }
    private int bottomStacker(Map gameState){//returns size of ArrayList at the given x,y
        System.out.println(gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size());
        
        return gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size();
    }
    protected boolean checkOwner(UnitTypes serverSide , String ID){
        System.out.println("Serverside Objects Owner: " + serverSide.getOWNER());
        System.out.println("command issuers ID: " +  ID);
        return ID.equals(serverSide.getOWNER());
        
    }
}
