/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Faction.Member;
import Faction.NoTeamAssociationException;
import Faction.Team;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author brenn
 */
abstract public class AbstractUnitAttack extends AbstractUnit{
    protected TargetingParameters UnitTargetTypes;
    abstract public void attack(GodsofWargame mapState);
    protected static int trueRangeUp(int radius,UnitTypes unit ){
        //System.out.println("trueRangeUP: " + Math.max(0, unit.getUyPos() - radius));
        return Math.max(0, unit.getUyPos() - radius);
    }
    protected static int trueRangeLeft(int radius,UnitTypes unit){
        //System.out.println("TrueRangeLeft: " + Math.max(0,unit.getUxPos() - radius));
        return Math.max(0,unit.getUxPos() - radius);
    }
    protected static int trueRangeRight(int radius,UnitTypes unit , Map mapState){
        //System.out.println("trueRangeRight: " + Math.min(mapState.getCol(),unit.getUxPos() + radius));
        return Math.min(mapState.getRow() -1 ,unit.getUxPos() + radius);
    }
    protected static int trueRangeDown(int radius,UnitTypes unit , Map mapState){
        //System.out.println("trueRangeDown: " + Math.min(mapState.getRow(),unit.getUyPos() + radius));
        return Math.min(mapState.getCol() -1 ,unit.getUyPos() + radius);
    }
    protected boolean validateTeam(UnitTypes attacker, UnitTypes target, GodsofWargame gameState) throws NoTeamAssociationException{
        

        Team defendingTeam = gameState.returnPlayerTeam(gameState.getMapState().getPlayer(target.getOWNER()).getPlayerMember());
        Team attackingOwner = gameState.returnPlayerTeam(gameState.getMapState().getPlayer(attacker.getOWNER()).getPlayerMember());
        return !defendingTeam.equals(attackingOwner);
        
    }
}
