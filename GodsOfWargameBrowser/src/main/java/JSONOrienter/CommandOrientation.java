/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.UnitTypeAdapter;
import com.godsofwargame.backend.UnitTypes;
import com.godsofwargame.commands.commandInterface;
import com.godsofwargame.commands.createUnitCommand;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

/**
 *
 * @author brenn
 */
public class CommandOrientation{
    private final String OriginalJSON;
    GodsofWargame gameState;
    public CommandOrientation(String commandJSON, GodsofWargame gameState){
        OriginalJSON = commandJSON;
        this.gameState = gameState;
    }
    /* A method which creates a command that is ready to execute, it gets a command to the starting line
    */
    public commandInterface commandGetStart() throws InvalidJSONException{
        
        System.out.println("Got to GetStart");
        
        
        //JsonObject a = toMessage.fromJson(OriginalJSON, JsonObject.class);
        //System.out.println(a.keySet().toString() + " Testing new JSON handling");
        SeperatedSimpleMessage seperated = getAsMessage();
        
        //System.out.println(seperated.header.getAsJsonObject().get("className").getAsJsonPrimitive().getAsString());
        //TODO implent @JsonAdapter() to commands and units so that This awful code can be removed
        GsonBuilder build = new GsonBuilder();
        Gson toCommand  =   build.registerTypeAdapter(commandInterface.class,
                            new TypeAdaptorCommand(seperated.header.getAsJsonObject().get("className").getAsJsonPrimitive().getAsString())).create();
        

        System.out.println("CommandOrientation: Right before CommandInterface");
        commandInterface command = toCommand.fromJson(seperated.body, commandInterface.class);
        System.out.println("CommandOrientation: Succesfully built command");
        if(seperated.header.getAsJsonObject().get("unitPresence").getAsJsonPrimitive().getAsBoolean()){
            GsonBuilder unitTypeFinder = new GsonBuilder();
            Gson toUnit     =   unitTypeFinder.registerTypeAdapter(UnitTypes.class, 
                                new TypeAdaptorUnit(seperated.body.getAsJsonObject().get("unitObject")) ).create();//NOTE if Units are passed as an array in future then a frontend change storing all units in array will be necessary
            UnitTypes a = toUnit.fromJson(seperated.body.getAsJsonObject().get("unitObject"), UnitTypes.class);
            //System.out.println(a.toString());
            a.prepare(gameState);
            System.out.println("Testing UnitValue: " + a.toString());
            command.setUnit(a);
        }
        
        
        
        return command; //return buildCommand(
            
    }
    private SeperatedSimpleMessage getAsMessage(){
        Gson toMessage = new Gson();
        JsonObject a = toMessage.fromJson(OriginalJSON, JsonObject.class);
        return new SeperatedSimpleMessage(a);
    }
}
