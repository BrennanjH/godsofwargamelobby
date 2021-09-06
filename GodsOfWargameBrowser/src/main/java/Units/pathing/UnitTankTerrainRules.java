/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import Location.Terrain;
import java.util.ArrayList;
import java.util.List;

/**Acts as a template object that when constructed automatically defines terrain valid for tanks
 * If a Unit moves like a tank it is forcibly moved like this.
 *
 * @author brenn
 */
public class UnitTankTerrainRules implements TerrainRules{
    
    private final List<String> badSpots;
    public UnitTankTerrainRules(){
        badSpots = new ArrayList<>();
        badSpots.add("MOUNTAIN");
    }
    
    @Override
    public boolean isValid(Terrain terrain) {
        for(String s : badSpots){
            if(terrain.getType().equals(s))
                return false;
        }
        return true;
    }

    @Override
    public void addBadSpot(String invalidTerrainString) {
        
        //This class is meant to act more as a template so it shouldn't allow new terrain Alterations after creation
    }

    @Override
    public List<String> getBadSpots() {
        return badSpots;
    }
    @Override
    public String toString(){
        return badSpots.toString();
    }
    
}
