/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitCommandStructure;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.mapUpdater;

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
                //Charge the players account
                gameState.getMapState().getPlayer(Id).changeMoney(cost * -1);
                
                //The new command Unit should be positioned on the map
                mover.setUzPos(bottomStacker(gameState.getMapState()));
                mapUpdater.newUnitState(gameState.getMapState(),mover);
                
                //Create a territory piece below the commander so that new units can be spawned in where the commander was spawned
                
                
                //Add the commanders to gameState so future references are easier to get
                gameState.addCommander(mover);//IMPORTANT LINE OF CODE FOR COMMANDERS
                
                System.out.println("create Unit Command successful");
            }
        }
        
        //return listHolder;
    }
    private int bottomStacker(Map gameState){//returns size of ArrayList at the given x,y
        //System.out.println(gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size());
        return gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size();
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
}
