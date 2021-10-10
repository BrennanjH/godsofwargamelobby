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
                    mover.setOwnerMember(gameState.getMapState().getPlayer(Id).getPlayerMember());
                    bottomStacker(gameState.getMapState());
                    
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
                + tankPrice;
    }
}
