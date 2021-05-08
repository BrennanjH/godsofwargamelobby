/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import Units.pathing.AbstractUnitMovement;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.mapUpdater;

/**
 *
 * @author brenn
 */
public class UnitPlaneMovement extends AbstractUnitMovement{
    UnitTypes mover;
    public UnitPlaneMovement(UnitTypes unit){
        mover = unit;
    }
    @Override
    public void beginMovement(){
        
    }
    @Override
    public void move(GodsofWargame gameState,int newX,int newY, String ID){
        //jsonsendHolder moveData = new jsonsendHolder();
        UnitTypes serverProof = gameState.getMapState().getUnitTypeinDeployedForces(mover.getUxPos(), mover.getUyPos(), mover.getUzPos());
        System.out.println("is check Owner: " + checkOwner(serverProof, ID));
        
        if(checkOwner(serverProof, ID) ){//WARNING may have an y,x bug here
            System.out.println("Move called and succeeded");
            moving = true;
            
            UnitTypes temp = serverProof;//creating a new unit to take on changed properties without altering original reference.
            
            temp.setUxPos(newX);
            temp.setUyPos(newY);
            temp.setUzPos(bottomStacker(gameState.getMapState()));
            
            
        ////Following code is for updating frontend/backend systems///////////////////////////////////////////////////////////
            //moveData.addUnitForDeletion(serverProof);//unit passed in JSON for frontend use
            //moveData.addUnit(temp);//unit passed in JSON for frontend use
            mapUpdater.updateUnitState(gameState.getMapState(),mover,temp);//caling STATIC method to update backend lists
        }
        
        //return moveData;
    }
    private int bottomStacker(Map gameState){//returns size of ArrayList at the given x,y
        System.out.println(gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size());
        return gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size() -1;
    }
    protected boolean checkOwner(UnitTypes serverSide , String ID){
        System.out.println("Serverside Objects Owner: " + serverSide.getOWNER());
        System.out.println("command issuers ID: " +  ID);
        return ID.equals(serverSide.getOWNER());
        
    }
}
