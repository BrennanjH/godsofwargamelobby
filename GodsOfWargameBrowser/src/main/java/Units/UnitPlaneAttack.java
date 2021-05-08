/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;

/**
 *
 * @author brenn
 */
public class UnitPlaneAttack extends AbstractUnitAttack{
    UnitTypes attacker;
    final int yIndexStart;
    final int yIndexStop;
    final int xIndexStart;
    final int xIndexStop;
    public UnitPlaneAttack(UnitTypes unit ,GodsofWargame gameState){
        attacker = unit;
        yIndexStart = trueRangeUp(attacker.getUrange(), attacker);
        yIndexStop = trueRangeDown(attacker.getUrange(), attacker,gameState.getMapState());
        xIndexStop = trueRangeRight(attacker.getUrange(), attacker,gameState.getMapState());
        xIndexStart = trueRangeLeft(attacker.getUrange(), attacker);
    }
    @Override
    public void attack(GodsofWargame gameState){
        for(int i = xIndexStart; i <= xIndexStop;i++){
            for(int j = yIndexStart; j <= yIndexStop;j++){
                if (! (gameState.getMapState().getDeployedForces()[i][j].isEmpty())){
                    if ( !(attacker.getOWNER().equals(gameState.getMapState().getUnitTypeinDeployedForces(i, j, 0).getOWNER())) ){ 
                        //System.out.println("UnitTank is shooting: "+ mover.getOWNER());
                        shootSquare(i,j,gameState.getMapState());
                        return;
                    }
                }
            }
        }
        //System.out.println(mover.getOWNER() + " : Didn't Fire");
    }
    private void shootSquare(int row,int col, Map mapState){
        for(int i = 0; i < mapState.getDeployedForces()[row][col].size(); i++){
            mapState.getUnitTypeinDeployedForces(row, col, i).changeUhealth(attacker.getUdamage());
        }
        
    }
    public static int trueRangeUp(int radius,UnitTypes unit ){
        return Math.max(0, unit.getUyPos() - radius);
    }
    public static int trueRangeLeft(int radius,UnitTypes unit){
        return Math.max(0,unit.getUxPos() - radius);
    }
    public static int trueRangeRight(int radius,UnitTypes unit , Map mapState){
        return Math.min(mapState.getCol(),unit.getUxPos() + radius);
    }
    public static int trueRangeDown(int radius,UnitTypes unit , Map mapState){
        return Math.min(mapState.getRow(),unit.getUyPos() + radius);
    }
}
