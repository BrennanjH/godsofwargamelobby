/*
 * To change mover license header, choose License Headers in Project Properties.
 * To change mover template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.Map;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.mapUpdater;

/**
 *
 * @author brenn
 */
public class UnitTankMovement extends AbstractUnitMovement{
    UnitTypes mover;
    TerrainRules terrainRules;
    int lx,ly;
    Routing path;
    public UnitTankMovement(UnitTypes unit){
        mover = unit;
        terrainRules.
        //TODO define the terrain rules class so that it can be used by tanks
    }
    @Override
    public void beginMovement(){
        if(mover.getUxPos() == lx && mover.getUyPos() == ly)
            moving= false;
        else{
            PathLogic pather = new Pather();
            pather.calcPath(mover.getUxPos(), mover.getUyPos(), lx, ly);
        }
    }
    //Generates Routing object
    @Override
    public void move(GodsofWargame gameState,int newX,int newY, String ID){
        //jsonsendHolder moveData = new jsonsendHolder();
        
        UnitTypes serverProof = gameState.getMapState().getUnitTypeinDeployedForces(mover.getUxPos(), mover.getUyPos(), mover.getUzPos());
        System.out.println("is check Owner: " + checkOwner(serverProof, ID));
        
        if(gameState.getMapState().getTerrain(mover.getUxPos(), mover.getUyPos()).getType() == 0 && checkOwner(serverProof, ID) ){//WARNING may have an y,x bug here
            System.out.println("Move called and succeeded");
            moving = true;
            path = new Routing();
            lx=newX;
            ly=newY;
            
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
        
        return gameState.getDeployedForces()[mover.getUxPos()][mover.getUyPos()].size();
    }
    protected boolean checkOwner(UnitTypes serverSide , String ID){
        System.out.println("Serverside Objects Owner: " + serverSide.getOWNER());
        System.out.println("command issuers ID: " +  ID);
        return ID.equals(serverSide.getOWNER());
        
    }
}
