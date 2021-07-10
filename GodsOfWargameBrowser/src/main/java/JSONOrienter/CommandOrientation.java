/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.commandInterface;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 *
 * @author brenn
 */
public class CommandOrientation{
    private final String OriginalJSON;
    
    public CommandOrientation(String commandJSON){
        OriginalJSON = commandJSON;
    }
    /*
    //implies existance of Hard start which would be used to create a new JSONHandler
    //With new properties that can handle the unique message, Essentially a theoretical
    class that exists for abnormal but known JSON cases. NON known cases should throw error.
    */
    public commandInterface commandSoftStart(){
        JsonParser jsonParser = new JsonParser();
        JsonObject jo = (JsonObject)jsonParser.parse(OriginalJSON);
        return null;
    }
    //A class which exists to handle abnormal JSON messages that can't be assumed
    //to be an error.
    private JsonObject commandKickStart() throws InvalidJSONException {
        return null;
    }
        
        
}
