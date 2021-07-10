/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.commandInterface;
import com.godsofwargame.backend.interfaceAdapter;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.peerSpecificIdentifier;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;


/** When JSON is sent to server something needs to read it and get into a form
 * The rest of the backend can use. This is accomplished by this class as a one 
 * line command.
 *
 * @author brenn
 */
public class JSONInternalHandler implements JSONHandler{
    private GodsofWargame gameState;
    //private String id;
    public JSONInternalHandler(GodsofWargame gameState){
        this.gameState = gameState;
        
    }
    @Override
    public HashMap<String, String> serialize(){
        HashMap<String, String> serializedData = new HashMap<>();
        convertToString(peerSpecificIdentifier.sortData(gameState), serializedData);
        return serializedData;
    }
    @Override
    public void deserialize(String incoming ){
        CommandOrientation head = new CommandOrientation(incoming);
        head.commandSoftStart();
    }
    
    private HashMap<String, String> convertToString(HashMap<String, jsonsendHolder> objectData, HashMap<String, String> Stringdata){
        //forLoopkeys = objectData.keySet();
        Gson serializer = new Gson();
        for (String s : objectData.keySet()){
            Stringdata.put(s, serializer.toJson(objectData.get(s)));
        }
        return Stringdata;
    }
}
