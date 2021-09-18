/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import Location.Terrain;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author brenn
 */
public class UnitPlaneTerrainRules implements TerrainRules{
    private final List<String> badSpots;
    public UnitPlaneTerrainRules(){
        badSpots = new ArrayList<>();
        badSpots.add("");
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getBadSpots() {
        return badSpots;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
