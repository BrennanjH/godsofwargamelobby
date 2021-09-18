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
                gameState.getMapState().getPlayer(Id).changeMoney(cost * -1);
                mover.setUzPos(bottomStacker(gameState.getMapState()));
                //listHolder.addUnit(mover);

                mapUpdater.newUnitState(gameState.getMapState(),mover);
                System.out.println("create Unit Command successful");

                gameState.addCommander(mover);//IMPORTANT LINE OF CODE FOR COMMANDERS
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
