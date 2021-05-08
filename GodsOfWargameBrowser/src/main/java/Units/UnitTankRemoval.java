/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypes;

/**
 *
 * @author brenn
 */
public class UnitTankRemoval extends AbstractUnitRemoval{
    UnitTypes mover;//NOTE that UnitTypes is used not UnitTank because this class is to be usable by any Unit
    public UnitTankRemoval(UnitTypes unit){
        mover = unit;
    }
    @Override
    public void removeUnit(GodsofWargame gameState){
        if(mover.getUhealth() <= 0){
            gameState.getMapState().removeUnitTypeinDeployedForces(mover.getUxPos() , mover.getUyPos(), mover);
        }
    }
}
