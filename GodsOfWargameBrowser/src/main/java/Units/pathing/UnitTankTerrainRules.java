/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import com.godsofwargame.backend.Terrain;

/**Acts as a template object that when constructed automatically defines terrain valid for tanks
 * If a Unit moves like a tank it is forcibly moved like this.
 *
 * @author brenn
 */
public class UnitTankTerrainRules implements TerrainRules{

    final String[] badSpots = {"MOUNTAIN"};
    
    @Override
    public boolean isValid(Terrain terrain) {
        for(String s : badSpots){
            if(terrain.getType().equals(s))
                return false;
        }
        return true;
    }
    
    
}
