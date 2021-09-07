/*
 * To change mover license header, choose License Headers in Project Properties.
 * To change mover template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.mapUpdater;

/**
 *
 * @author brenn
 */
public class UnitTankMovement extends AbstractUnitMovement{
    UnitTypes mover;
    //TerrainRules terrainRules;
    //int lx,ly;
    //Routing path;
    GodsofWargame gameState;
    //constructor to be used by new units that have tank pathing but not tank terrain interactions
    public UnitTankMovement(UnitTypes unit, GodsofWargame gameState) {
        mover = unit;
        this.gameState = gameState;
        //terrainRules = unit.getTerrainRules();
    }
    @Override
    public void beginMovement(){
        
    }
    //Generates Routing object
    @Override
    public void move(GodsofWargame gameState){
        //set Necessary fields
        this.gameState = gameState;
        //validate movement
        System.out.println("unitTankMovement: move: " + path.pathingRoute);
        int newX = path.getPathingRoute().get(1)[0];
        int newY = path.getPathingRoute().get(1)[1];
        if ( validate(newX, newY ) ) {
            //move unit to next square
            gameState.getMapState().removeUnitTypeinDeployedForces(mover.getUxPos(), mover.getUyPos(), mover);
            mover.setUxPos(newX);
            mover.setUyPos(newY);
            gameState.getMapState().addDeployedForces(mover.getUxPos(), mover.getUyPos(), mover);
            path.pathingRoute.remove(0);
        } else {
            //If validation fails sets the route to an empty route
            path = new Routing();   
        }
    }
    private int bottomStacker(Map gameState){//returns size of ArrayList at the given x,y
        System.out.println(gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size());
        
        return gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size();
    }
    private boolean validate(int newX, int newY){
        //Validate: {terrain, next Position
                //Validate Terrain
        return mover.getTerrainRules().isValid(gameState.getMapState().getTerrain(newX, newY)) &&
                //Validate that the new positions path is not too far away from current location
                ( (Math.abs((mover.getUxPos() + mover.getUyPos()) - (newX + newY))) <= 2 );
    }
}
