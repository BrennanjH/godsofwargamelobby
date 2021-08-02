/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.commands.commandInterface;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.peerSpecificIdentifier;
import com.godsofwargame.commands.DeserializationErrorCommand;
import com.google.gson.Gson;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


/** When JSON is sent to server something needs to read it and get into a form
 * The rest of the backend can use. This is accomplished by this class as a one 
 * line command.
 *
 * @author brenn
 */
public class CommandHandler implements JSONHandler{
    private GodsofWargame gameState;
    //private String id;
    public CommandHandler(GodsofWargame gameState){
        this.gameState = gameState;
        
    }
    //Essentially serializes gameState, a conveniance method that ideally shouldn't be used but it's too hard to always presort data before trying to serialize
    @Override
    public HashMap<String, String> serialize(){
        HashMap<String, String> serializedData = new HashMap<>();
        convertToString(peerSpecificIdentifier.sortData(gameState), serializedData);
        return serializedData;
    }
    @Override
    public HashMap<String, String> serialize(HashMap<String, jsonsendHolder> objectData){
        HashMap<String, String> serializedData = new HashMap<>();
        convertToString(objectData, serializedData);
        return serializedData;
    }
    @Override
    public commandInterface deserialize(String incoming ){
        CommandOrientation head = new CommandOrientation(incoming);
        System.out.println("Got to deserialize");
        
        try {
            return head.commandGetStart();
        } catch (InvalidJSONException ex) {
            Logger.getLogger(CommandHandler.class.getName()).log(Level.SEVERE, null, ex);
            return new DeserializationErrorCommand();
        }
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
