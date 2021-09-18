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
    transient final int yIndexStart;
    transient final int yIndexStop;
    transient final int xIndexStart;
    transient final int xIndexStop;
    String[] interactions = {"GROUND"};
    public UnitPlaneAttack(UnitTypes unit ,GodsofWargame gameState){
        attacker = unit;
        yIndexStart = trueRangeUp(attacker.getUrange(), attacker);
        yIndexStop = trueRangeDown(attacker.getUrange(), attacker,gameState.getMapState());
        xIndexStop = trueRangeRight(attacker.getUrange(), attacker,gameState.getMapState());
        xIndexStart = trueRangeLeft(attacker.getUrange(), attacker);
        UnitTargetTypes = new TargetingParameters(interactions);
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
        //System.out.println("trueRangeUP: " + Math.max(0, unit.getUyPos() - radius));
        return Math.max(0, unit.getUyPos() - radius);
    }
    public static int trueRangeLeft(int radius,UnitTypes unit){
        //System.out.println("TrueRangeLeft: " + Math.max(0,unit.getUxPos() - radius));
        return Math.max(0,unit.getUxPos() - radius);
    }
    public static int trueRangeRight(int radius,UnitTypes unit , Map mapState){
        //System.out.println("trueRangeRight: " + Math.min(mapState.getCol(),unit.getUxPos() + radius));
        return Math.min(mapState.getCol() -1 ,unit.getUxPos() + radius);
    }
    public static int trueRangeDown(int radius,UnitTypes unit , Map mapState){
        //System.out.println("trueRangeDown: " + Math.min(mapState.getRow(),unit.getUyPos() + radius));
        return Math.min(mapState.getRow() -1 ,unit.getUyPos() + radius);
    }
}
