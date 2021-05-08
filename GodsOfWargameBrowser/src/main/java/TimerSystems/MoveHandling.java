/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimerSystems;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.jsonsendHolder;

/**
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
        for(int i=0; i<gameState.getMapState().getRow();i++){
            //System.out.println("First loop entered");
            for(int j=0; j<gameState.getMapState().getCol();j++){
               // System.out.println("Second loop entered");
                for(int k = 0; k<gameState.getMapState().getDeployedForces()[i][j].size();k++){
                    // System.out.println("Third loop entered");
                    if (gameState.getMapState().getUnitTypeinDeployedForces(i,j,k).getMoveHandler().moving){
                        gameState.getMapState().getUnitTypeinDeployedForces(i,j,k).getMoveHandler().beginMovement();
                    }
                }
            }
        }
    }
    
}
