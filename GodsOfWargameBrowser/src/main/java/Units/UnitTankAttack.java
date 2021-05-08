/*
 * To change mover license header, choose License Headers in Project Properties.
 * To change mover template file, choose Tools | Templates
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
public class UnitTankAttack extends AbstractUnitAttack{
    UnitTypes mover;
    final int yIndexStart;
    final int yIndexStop;
    final int xIndexStart;
    final int xIndexStop;
    public UnitTankAttack(UnitTypes unit,GodsofWargame gameState){
        mover = unit;
        yIndexStart = trueRangeUp(mover.getUrange(), mover);
        yIndexStop = trueRangeDown(mover.getUrange(), mover,gameState.getMapState());
        xIndexStop = trueRangeRight(mover.getUrange(), mover,gameState.getMapState());
        xIndexStart = trueRangeLeft(mover.getUrange(), mover);
        }
    //TODO fix the index so that they aren't recaculated each attack cycle
    //TODO tanks shouldn't hit certain unit types so this needs to added
    @Override
    public void attack(GodsofWargame gameState){
        //System.out.println("yIndexStart: " + yIndexStart+" yIndexStop: " +yIndexStop +" xIndexStart: " + xIndexStart+" xIndexStop: " + xIndexStop);
        for(int i = xIndexStart; i <= xIndexStop;i++){
            for(int j = yIndexStart; j <= yIndexStop;j++){
                
                if (  !(gameState.getMapState().getDeployedForces()[i][j].isEmpty())  ){
                    //System.out.println((gameState.getMapState().getDeployedForces()[i][j].isEmpty()));
                    if ( !(mover.getOWNER().equals(gameState.getMapState().getUnitTypeinDeployedForces(i, j, 0).getOWNER())) ){ 
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
            mapState.getUnitTypeinDeployedForces(row, col, i).changeUhealth(mover.getUdamage());
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
