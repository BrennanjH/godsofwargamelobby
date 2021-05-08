/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.godsofwargame.backend.GodsofWargame;

/**
 *
 * @author brenn
 */
abstract public class AbstractUnitRemoval extends AbstractUnit{
    abstract public void removeUnit(GodsofWargame GameState);
}
