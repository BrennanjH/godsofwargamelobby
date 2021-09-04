/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import Location.Terrain;
import java.util.List;

/** A simple class that if used allows the terrainRuleSet to change at runtime
 *
 * @author brenn
 */
public class GenericTerrainRules extends AbstractGenericTerrainRules{
    //private String[] badSpots;
    @Override
    public boolean isValid(Terrain terrain) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void addBadSpot(String invalidTerrainString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
/*
    @Override
    public List<String> getBadSpots() {
        return badSpots;
    }
    */
}
