/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.jsonsendHolder;

/**
 *
 * @author brenn
 */
abstract public class AbstractUnitCreate extends AbstractUnit{
    //public jsonsendHolder create(Map mapState, String Id);
    abstract public void create(GodsofWargame gameState, String Id);
    final int healthPrice=10,damagePrice=10,rangePrice=50,speedPrice=30;
}
