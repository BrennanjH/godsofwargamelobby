/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.commandInterface;
import com.godsofwargame.backend.interfaceAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/** When JSON is sent to server something needs to read it and get into a form
 * The rest of the backend can use. This is accomplished by this class as a one 
 * line command.
 *
 * @author brenn
 */
public class JSONInternalHandler implements JSONHandler{
    
    @Override
    public String serialize(){
        return "";
    }
    @Override
    public void deserialize(String incoming){
        GsonBuilder build = new GsonBuilder();//these objects manage creating of commandObjects
        Gson deserializer = build.registerTypeAdapter(commandInterface.class, new interfaceAdapter()).create();
        commandInterface recievedCommand = deserializer.fromJson(incoming, commandInterface.class);
    }
}
