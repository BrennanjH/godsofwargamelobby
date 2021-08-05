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
public interface Territory {
    
    //Territory needs an owner, That Owner is not necesarrily one person so a team Object is returned
    public Team getFaction();
    public void setCoordinate(Coordinate location);
}
