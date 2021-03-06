/*
 * To change attacker license header, choose License Headers in Project Properties.
 * To change attacker template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;


import Faction.NoTeamAssociationException;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author brenn
 */
public class UnitTankAttack extends AbstractUnitAttack{
    UnitTypes attacker;
    String[] interactions = {"GROUND"};
    final int yIndexStart;
    final int yIndexStop;
    final int xIndexStart;
    final int xIndexStop;
    public UnitTankAttack(UnitTypes unit,GodsofWargame gameState){
        attacker = unit;
        yIndexStart = trueRangeUp(attacker.getUrange(), attacker);
        yIndexStop = trueRangeDown(attacker.getUrange(), attacker,gameState.getMapState());
        xIndexStop = trueRangeRight(attacker.getUrange(), attacker,gameState.getMapState());
        xIndexStart = trueRangeLeft(attacker.getUrange(), attacker);
        UnitTargetTypes = new TargetingParameters(interactions);
        
    }
    //TODO fix the index so that they aren't recaculated each attack cycle
    //TODO tanks shouldn't hit certain unit types so this needs to added
    @Override
    public void attack(GodsofWargame gameState){
        //System.out.println("yIndexStart: " + yIndexStart+" yIndexStop: " +yIndexStop +" xIndexStart: " + xIndexStart+" xIndexStop: " + xIndexStop);
        for(int i = xIndexStart; i <= xIndexStop;i++){
            for(int j = yIndexStart; j <= yIndexStop;j++){
                
                if ( !(gameState.getMapState().getDeployedForces()[i][j].isEmpty())  ){
                    
                    try {
                        UnitTypes target = gameState.getMapState().getUnitTypeinDeployedForces(i, j, 0);
                        
                        //check if team of attacker is equal to defender
                        
                        if ( validateTeam(attacker,target,gameState) 
                            && UnitTargetTypes.canShootProperty(target.getProperty())){
                        //System.out.println("UnitTank is shooting: "+ attacker.getOWNER());
                        shootSquare(i,j,gameState.getMapState());
                        return;
                    }
                    } catch (NoTeamAssociationException ex) {
                        Logger.getLogger(UnitTankAttack.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        //System.out.println(attacker.getOWNER() + " : Didn't Fire");
    }
    private void shootSquare(int row,int col, Map mapState){
        for(int i = 0; i < mapState.getDeployedForces()[row][col].size(); i++){
            mapState.getUnitTypeinDeployedForces(row, col, i).changeUhealth(attacker.getUdamage());
        }
    }
    
}
