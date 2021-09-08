/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import Units.pathing.AbstractUnitMovement;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.mapUpdater;

/**
 *
 * @author brenn
 */
public class UnitPlaneMovement extends AbstractUnitMovement{
    UnitTypes mover;
    public UnitPlaneMovement(UnitTypes unit){
        mover = unit;
    }
    
    @Override
    public void move(GodsofWargame gameState){
        
    }
    private int bottomStacker(Map gameState){//returns size of ArrayList at the given x,y
        System.out.println(gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size());
        return gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size() -1;
    }
    protected boolean checkOwner(UnitTypes serverSide , String ID){
        System.out.println("Serverside Objects Owner: " + serverSide.getOWNER());
        System.out.println("command issuers ID: " +  ID);
        return ID.equals(serverSide.getOWNER());
        
    }
}
