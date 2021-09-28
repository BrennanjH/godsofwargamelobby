/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import Faction.Member;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;

/**
 *
 * @author brenn
 */
abstract public class AbstractUnitCreate extends AbstractUnit{
    //public jsonsendHolder create(Map mapState, String Id);
    abstract public void create(GodsofWargame gameState, String Id);
    final int healthPrice=10,damagePrice=10,rangePrice=50,speedPrice=30;
    
    public boolean validateTerritory(int x, int y, Member playerData , Map mapState) {
        if( mapState.getLandOwnership()[x][y] != null)
            return mapState.getLandOwnership()[x][y].getFaction().getTeam().contains(playerData);
        else
            return false;
    }
}
