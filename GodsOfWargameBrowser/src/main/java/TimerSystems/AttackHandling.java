/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimerSystems;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypes;

/**
 *
 * @author brenn
 */
public class AttackHandling implements Handling{
    GodsofWargame gameState;
    public AttackHandling(GodsofWargame GameState){
        gameState = GameState;
    }
    @Override 
    public void Handle(){
        //TODO Fix outofboundsexception in attacking system
        //results.setDeleteUnitList(peerSpecificIdentifier.getUnitsAsList(gameState));
        fire();//All units attack at once
        removeDead();//Units that died are removed from gameState
    }
    //Goes through the Unit list and sets each unit's zPos to it's respective position in the Array
    //I bet theres a way to set the zPos to the reference of it's index so that changes are automatic
    private void updateZPos(int x, int y){
        //System.out.println(gameState.getMapState().getDeployedForces()[x][y].size());
        for(int i=0;i<gameState.getMapState().getDeployedForces()[x][y].size();i++){
            UnitTypes hold = gameState.getMapState().getUnitTypeinDeployedForces(x,y,i);
            hold.setUzPos(i);
        }
    }
    
    //Each Unit fires but doesn't get removed for fairness, 
    private void fire(){
        for(int i=0; i<gameState.getMapState().getRow();i++){
            //System.out.println("First loop entered");
            for(int j=0; j<gameState.getMapState().getCol();j++){
               // System.out.println("Second loop entered");
                for(int k = 0; k<gameState.getMapState().getDeployedForces()[i][j].size();k++){
                    // System.out.println("Third loop entered");
                    gameState.getMapState().getUnitTypeinDeployedForces(i,j,k)//Gets UnitTypes object
                             .attack(gameState);//Uses UnitTypes objects attack() method
                }
            }
        }
        
    }
    //Loops through Unit List and removes dead Units
    private void removeDead(){
        
        for (int i=0; i < gameState.getMapState().getRow();i++){
            for (int j =0;j<gameState.getMapState().getCol();j++){
                /* Looks better but will throw exception since removeUnit is altering the list as the for loop goes through it
                for(UnitTypes s : gameState.getMapState().getListInDeployedForces(i,j)){
                    s.removeUnit(gameState);//Cause Iteration Error since it is removing from the list that defines this.
                }
                */
                //To improve For Loop create on run through tag the units to be removed,after loop remove them, then update them
                for(int k =0; k < gameState.getMapState().getDeployedForces()[i][j].size(); k++){
                    int size = gameState.getMapState().getDeployedForces()[i][j].size();
                    gameState.getMapState().getUnitTypeinDeployedForces(i,j,k).removeUnit(gameState); 
                    
                    if(size > gameState.getMapState().getDeployedForces()[i][j].size())
                        k--;
                }
                
                updateZPos(i,j);
            }
        }
        
    }
}
