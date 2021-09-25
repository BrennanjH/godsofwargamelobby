/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitCommandStructure;
import com.godsofwargame.commands.GameEndHandler;
/**
 *
 * @author brenn
 */
public class UnitCommandStructureRemoval extends AbstractUnitRemoval{
    UnitCommandStructure mover;
    public UnitCommandStructureRemoval(UnitCommandStructure unit){
        mover = unit;
    }
    @Override
    public void removeUnit(GodsofWargame gameState){
        if(mover.getUhealth() <= 0){
            //removal of commander from gameState
            gameState.getMapState().removeUnitTypeinDeployedForces(mover.getUxPos(), mover.getUyPos(), mover);
            gameState.removeCommander(mover);
            //Since this is a commander a check needs be run to determine the fate of the game
            GameEndHandler ender = new GameEndHandler(mover.getOWNER(), gameState);
            ender.execute();
        }
    }
}
