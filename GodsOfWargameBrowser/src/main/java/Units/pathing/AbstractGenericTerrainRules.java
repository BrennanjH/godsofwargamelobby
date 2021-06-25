/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

/** Extensions of GenericTerrainRules must be dynamically created so they have 
 * to have a builder method at instantiation 
 *
 * @author brenn
 */
abstract public class AbstractGenericTerrainRules implements TerrainRules{
    //a list of strings that if they align with a terrain's type then they are removed
    String[] badSpots;
    //A class that all subclasses can use to change how they use, 
    public void builder(String[] badSpots){
        this.badSpots = badSpots;
    }
    
}
