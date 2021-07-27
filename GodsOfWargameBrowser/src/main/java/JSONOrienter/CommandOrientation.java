/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.commandInterface;
import com.godsofwargame.backend.createUnitCommand;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
    public commandInterface commandGetStart() throws InvalidJSONException{
        
            System.out.println("Got to GetStart");
            Gson toMessage = new Gson();
            
            JsonObject a = toMessage.fromJson(OriginalJSON, JsonObject.class);
            System.out.println(a.getAsString() + " Testing new JSON handling");
            
            return new createUnitCommand();
    }
}
