/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimerSystems;
import JSONOrienter.JSONHandler;
import JSONOrienter.CommandHandler;
import com.godsofwargame.backend.DataDistributer;
import com.godsofwargame.backend.GodsofWargame;
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
    Handling currencyInit;
    public TimerScheduler(GodsofWargame GameState){
        gameState = GameState;
        attackInit = new AttackHandling(GameState);
        moveInit = new MoveHandling(GameState);
        currencyInit = new CurrencyHandler(GameState);
    }
    @Override
    public void run(){
        try{
            
            attackInit.Handle();
            moveInit.Handle();
            currencyInit.Handle();
            updatePlayers();
        }
        catch (Exception E){
            E.printStackTrace();
        }
    }
    private void updatePlayers(){
        
        //JSONhandler passer = new JSONhandler();
        JSONHandler passer = new CommandHandler(gameState);
        //HashMap<String, String> serializedData = new HashMap<>();
        //serializedData = passer.convertToString(peerSpecificIdentifier.sortData(gameState), serializedData);
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
        
    }
    
    public Handling getMoveInit() {
        return moveInit;
    }

}


