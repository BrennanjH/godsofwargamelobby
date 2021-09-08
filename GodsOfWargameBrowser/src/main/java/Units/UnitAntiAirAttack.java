/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

import static Units.UnitTankAttack.trueRangeDown;
import static Units.UnitTankAttack.trueRangeLeft;
import static Units.UnitTankAttack.trueRangeRight;
import static Units.UnitTankAttack.trueRangeUp;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;

/**
 *
 * @author brenn
 */
public class UnitAntiAirAttack extends AbstractUnitAttack{
    UnitTypes attacker;
    String[] interactions = {"AIR"};
    final int yIndexStart;
    final int yIndexStop;
    final int xIndexStart;
    final int xIndexStop;
    GodsofWargame gameState;
    //private final boolean targetsAir = true;
    public UnitAntiAirAttack(UnitTypes attacker, GodsofWargame gameState) {
        this.gameState = gameState;
        this.attacker = attacker;
        yIndexStart = trueRangeUp(attacker.getUrange(), attacker);
        yIndexStop = trueRangeDown(attacker.getUrange(), attacker,gameState.getMapState());
        xIndexStop = trueRangeRight(attacker.getUrange(), attacker,gameState.getMapState());
        xIndexStart = trueRangeLeft(attacker.getUrange(), attacker);
        UnitTargetTypes = new TargetingParameters(interactions);
    }
    @Override
    public void attack(GodsofWargame mapState) {
        for(int i = xIndexStart; i <= xIndexStop;i++){
            for(int j = yIndexStart; j <= yIndexStop;j++){
                
                if ( !(gameState.getMapState().getDeployedForces()[i][j].isEmpty())  ){
                    UnitTypes target = gameState.getMapState().getUnitTypeinDeployedForces(i, j, 0);
                    //System.out.println((gameState.getMapState().getDeployedForces()[i][j].isEmpty()));
                    if ( !(attacker.getOWNER().equals(target.getOWNER()))
                            && UnitTargetTypes.canShootProperty(target.getProperty())){ 
                        //System.out.println("UnitTank is shooting: "+ attacker.getOWNER());
                        shootSquare(i,j,gameState.getMapState());
                        return;
                    }
                }
            }
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
    private void shootSquare(int row,int col, Map mapState){
        for(int i = 0; i < mapState.getDeployedForces()[row][col].size(); i++){
            mapState.getUnitTypeinDeployedForces(row, col, i).changeUhealth(attacker.getUdamage());
        }
        
    }
}
