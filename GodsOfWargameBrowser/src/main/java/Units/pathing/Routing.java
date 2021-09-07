/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import Location.Coordinate;
import java.util.ArrayList;
import java.util.List;

/**A class that represents the path that will be taken by the unit traveling.
 * For performance reasons as the unit moves it should remove it's prior location from route.
 * Failure to do so may open up pathing failures should old terrain transform.
 * Alternatives to removal would be to keep an index of cycles so that old squares are ignored
 * Uses ArrayList as opposed to Set so that the unit can loop back around and re-traverse land
 * @author brenn
 */
public class Routing {
    //public List<Coordinate> pathingRoute = new ArrayList<>();
    public List<int[]> pathingRoute = new ArrayList<>();

    public List<int[]> getPathingRoute() {
        return pathingRoute;
    }

    public void setPathingRoute(ArrayList<int[]> newRoute){
        pathingRoute = newRoute;
    }
    
}
