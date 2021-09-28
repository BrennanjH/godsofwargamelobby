/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
        //System.out.println(mover.getOWNER() + " : Didn't Fire");
    }
    private void shootSquare(int row,int col, Map mapState){
        for(int i = 0; i < mapState.getDeployedForces()[row][col].size(); i++){
            mapState.getUnitTypeinDeployedForces(row, col, i).changeUhealth(attacker.getUdamage());
        }
    }
    
    
}
