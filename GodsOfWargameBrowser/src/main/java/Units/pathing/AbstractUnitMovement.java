/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import Units.AbstractUnit;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.jsonsendHolder;

/**
 *
 * @author brenn
 */
abstract public class AbstractUnitMovement extends AbstractUnit{
    //public boolean moving = false;
    protected Routing path = new Routing();
    abstract public void move(GodsofWargame gameState);//Should generate routing object
    
    public void setPath(Routing newRoute){
        path = newRoute;
    }
    public Routing getPath(){
        return path;
    }
    public boolean isMoving(){
        return !(path.getPathingRoute().size() <= 1);
    }
}
