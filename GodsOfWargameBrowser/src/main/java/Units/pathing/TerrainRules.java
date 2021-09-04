/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import JSONOrienter.TerrainRulesAdaptor;
import Location.Terrain;
import com.google.gson.annotations.JsonAdapter;
import java.util.List;

/**UnitTypes should have a TerrainRules applied to them that defines how they 
 * interact with different Terrains
 *
 * @author brenn
 */
@JsonAdapter(TerrainRulesAdaptor.class)
public interface TerrainRules {
    //Used with TerrainRules implementations to define Terrain That isn't valid for movement different from 
    
    //implentations use isValid() to decide on weather or not a square is Valid
    //For pathing based on builder results. No builder call will mean everything is good
    public boolean isValid(Terrain terrain);
    public void addBadSpot(String invalidTerrainString);
    public List<String> getBadSpots();
}
