/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Location;

import Faction.Team;

/** An object that represents a literal square of territory 
 *
 * @author brenn
 */
public class ControlTerritory implements Territory {
    Team Owner;
    HighLevelCoord pos;
    public ControlTerritory(Team Owner, HighLevelCoord a) {
        this.Owner = Owner;
        pos = a;
    }
    
    @Override
    public Team getFaction() {
        return Owner;
    }

    @Override
    public void setCoordinate(Coordinate location) {
        pos = (HighLevelCoord) location;
    }
    
}
