/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Location;

import Faction.Team;
import com.godsofwargame.backend.GodsofWargame;

/** An object that represents a literal square of territory 
 *
 * @author brenn
 */
public class ControlTerritory implements Territory {
    Team Owner;
    Coordinate pos;
    public ControlTerritory(Team Owner, Coordinate a, GodsofWargame gameState) {
        this.Owner = Owner;
        pos =  a;
        
        //Register Territory with gameState mapstate
        gameState.getMapState().getLandOwnership()[a.getxPos()][a.getyPos()] = this; //Validation towards territory creation can be done in the future
    }
    
    @Override
    public Team getFaction() {
        return Owner;
    }

    @Override
    public void setCoordinate(Coordinate location) {
        pos = location;
    }
    
}
