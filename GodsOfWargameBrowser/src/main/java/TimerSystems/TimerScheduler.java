/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimerSystems;
import com.godsofwargame.backend.DataDistributer;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.JSONhandler;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.peerSpecificIdentifier;
import java.util.HashMap;
import java.util.TimerTask;
/**
 *
 * @author brenn
 */
public class TimerScheduler extends TimerTask{
    GodsofWargame gameState;
    Handling attackInit;
    Handling moveInit;
    public TimerScheduler(GodsofWargame GameState){
        gameState = GameState;
        attackInit = new AttackHandling(GameState);
        moveInit = new MoveHandling(GameState);
    }
    @Override
    public void run(){
        try{
        jsonsendHolder results = new jsonsendHolder();//Not really necessary but for now it's better
        attackInit.Handle();
        moveInit.Handle();
        updatePlayers();
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }
    private void updatePlayers(){
        
        JSONhandler passer = new JSONhandler();
        HashMap<String, String> serializedData = new HashMap<>();
        serializedData = passer.convertToString(peerSpecificIdentifier.sortData(gameState), serializedData);
        DataDistributer.distributeToPeers(gameState.getClients(), serializedData);
        
    }
    
    /*
    private void updatePlayers(jsonsendHolder data){
        
        JSONhandler passer = new JSONhandler();
        HashMap<String, String> serializedData = new HashMap<>();
        serializedData = passer.convertToString(peerSpecificIdentifier.sortData(gameState, data), serializedData);
        DataDistributer.distributeToPeers(gameState.getClients(), serializedData);
        
    }
    */

}


