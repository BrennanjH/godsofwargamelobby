/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

/**
 *
 * @author brenn
 */
import com.godsofwargame.commands.commandInterface;
import JSONOrienter.InterfaceAdapter;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import java.util.HashMap;

@Deprecated
public class JSONhandler  {
    GsonBuilder build = new GsonBuilder();//these objects manage creating of commandObjects
    Gson deserializingCommand = build.registerTypeAdapter(commandInterface.class, new InterfaceAdapter()).create();
    
    Gson serializer = new Gson();//Serializer for all outgoing data
    
    GsonBuilder unitTypeFinder = new GsonBuilder();
    Gson deserializingUnits = unitTypeFinder.registerTypeAdapter(UnitTypes.class, new UnitTypeAdapter() ).create();
    
    //Set<String> forLoopkeys;
    
    public void passMessage(String incoming,GodsofWargame gameState , String Id){//returns a string of a commandInterface object
        commandInterface command = deserializingCommand.fromJson(incoming, commandInterface.class);//TODO Hgher level interface can't handle subclass fix that
        System.out.println(command.testValue());
        UnitTypes alpha = deserializingUnits.fromJson(incoming, UnitTypes.class);
        System.out.println(alpha.testValue());
        command.setUnit(alpha);
        
        //HashMap<String, String> send = new HashMap<>();
        //send = convertToString( executer.processor(command, gameState, Id), send);
        
        //return send;
        //return serilizer.toJson(launch.processor(deserializing.fromJson(incoming, commandInterface.class), gameState));
        
    }
    public HashMap<String, String> convertToString(HashMap<String, jsonsendHolder> objectData, HashMap<String, String> Stringdata){
        //forLoopkeys = objectData.keySet();
        for (String s : objectData.keySet()){
            Stringdata.put(s, serializer.toJson(objectData.get(s)));
        }
        return Stringdata;
    }

}

