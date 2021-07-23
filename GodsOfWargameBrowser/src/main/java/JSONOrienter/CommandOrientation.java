/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.commandInterface;
import com.google.gson.Gson;
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
    public commandInterface commandGetStart() throws InvalidJSONException{
        
        try{
            System.out.println("Got to GetStart");
            Gson toCommand = new Gson();
            getHead();
            commandInterface temp = toCommand.fromJson(OriginalJSON, commandInterface.class);
            System.out.println("Got to testValue ");
            temp.testValue();
            System.out.println("Got to after testValue");
            
            return temp;
        }
        catch(InvalidJSONException E){
            throw E;
            //TODO create error responses that attempt to inform sender of message a failure to comprehend
        }
    }
    //generates a header
    private JSONHeader getHead() throws InvalidJSONException{
        
        return new JSONHeader();
    }
    
        
        
}
