/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimerSystems;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypes;
import java.util.ArrayList;
import java.util.List;

/** Decides which units are moving and then calls their movement helper class
 *
 * @author brenn
 */
public class MoveHandling implements Handling{
    GodsofWargame gameState;
    public MoveHandling(GodsofWargame GameState){
        gameState = GameState;
    }
    @Override 
    public void Handle(){
        List<UnitTypes> loopList = getUnitsAsList();
        for(UnitTypes t : loopList){
            if( t.getMoveHandler().isMoving()){
                //Decide how many times to loop through list
                int loopLength = Math.min(t.getUspeed(), t.getMoveHandler().getPath().getPathingRoute().size() -1 );
                
                for(int speedCheck=0; speedCheck < loopLength ; speedCheck++){
                   t.move(gameState);
                }
            }
        }
        
    }
    private List<UnitTypes> getUnitsAsList(){//Transforms unitList in Map into one List for serialization
        List<UnitTypes> temp = new ArrayList<>();
        for(int i =0; i<gameState.getMapState().getRow() ;i++){
            for(int j =0; j<gameState.getMapState().getCol() ;j++) {
                for(int k =0; k<gameState.getMapState().getDeployedForces()[i][j].size() ;k++){
                    
                    temp.add(gameState.getMapState().getUnitTypeinDeployedForces(i,j,k));
                    //System.out.println(gameState.getMapState().getUnitTypeinDeployedForces(i,j,k).toString());
                }
            }
        }
        return temp;
    }
    
}
