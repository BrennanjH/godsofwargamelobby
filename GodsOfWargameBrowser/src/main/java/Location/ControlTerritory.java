/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Location;

import Faction.Team;

/**
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCoordinate(Coordinate location) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
