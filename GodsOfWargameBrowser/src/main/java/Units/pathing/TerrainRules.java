/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

/**UnitTypes should have a TerrainRules applied to them that defines how they 
 * interact with different Terrains
 *
 * @author brenn
 */
public interface TerrainRules {
    //Used with TerrainRules implementations to define Terrain That isn't valid for movement
    public void Builder(String[] invalid);
    //implentations use isValid() to decide on weather or not a square is Valid
    //For pathing based on builder results. No builder call will mean everything is good
    public boolean isValid();
}
