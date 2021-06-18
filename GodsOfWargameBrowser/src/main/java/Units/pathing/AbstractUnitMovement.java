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
    public boolean moving = false;
    abstract public void move(GodsofWargame gameState,Routing movePath, String ID);//Should generate routing object
    abstract public void beginMovement();
    
}
