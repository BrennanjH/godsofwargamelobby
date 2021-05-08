/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import com.godsofwargame.backend.UnitTypes;

/**
 *
 * @author brenn
 */
public class Pather implements PathLogic{
    int difX ,difY;
    @Override
    public Routing calcPath(int startX,int startY, int endX, int endY){
        Routing chosenPath = new Routing();
        //init difX and difY so that the most efficient path can be searched before trying to go backwards.
        this.difX = endX - startX;
        this.difY = endY - startY;
        cardinalScanner();
        return chosenPath;
    }
    //This is public but it should probably be 
    @Override
    public void cardinalScanner(){
        
    }
    @Override
    public void cardinalScanner(int lastX, int lastY){
        
    }
}
