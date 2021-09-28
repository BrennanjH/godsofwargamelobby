/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Faction.Member;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.backend.mapUpdater;


/**
 *
 * @author brenn
 */
public class UnitTankCreate extends AbstractUnitCreate{
    UnitTypes mover;//NOTE that UnitTypes is used not UnitTank because this class is to be usable by any Unit
    final int tankPrice = 50;
    public UnitTankCreate(UnitTypes unit){
        mover = unit;
    }
    @Override
    public void create(GodsofWargame gameState, String Id){
        Member unitOwner = gameState.getMapState().getPlayer(Id).getPlayerMember();
        System.out.println("Unit Tank Member data: " + unitOwner);
        if (validateTerritory(mover.getUxPos(),mover.getUyPos(), unitOwner, gameState.getMapState())) {
            if(isTerrainValid(gameState.getMapState()) && Id.equals(mover.getOWNER())){
                int cost = getCost();
                if(cost < gameState.getMapState().getPlayer(Id).getMoney() ) {
                    gameState.getMapState().getPlayer(Id).changeMoney(cost * -1);
                    mover.setUzPos(bottomStacker(gameState.getMapState()));
                    //listHolder.addUnit(mover);

                    mapUpdater.newUnitState(gameState.getMapState(),mover);
                    System.out.println("create Unit Command successful");
                }

            }
        }
        //return listHolder;
    }
    
    //TODO fix bottom stacker so that it properly creates z cords
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
                + tankPrice;
    }
}
