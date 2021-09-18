/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import com.godsofwargame.commands.commandInterface;
import com.google.gson.Gson;
import java.io.IOException;

/**
 *
 * @author brenn
 */
//This class currently does two things but should do one
//First it sends to the sender their information, (ID)
//Second it sets up the map. This is it's true purpose and only the first player should ever bother sending this command
//The first purpose should be moved to an internal command that gets executed when OnOpen() in the endpoint get's called
//because the first purpose has not been moved there is a try catch block in here that really shouldn't be.
public class settingsCommand implements commandInterface{ 
    
    @Override
    public void execute(GodsofWargame gameState,String Id){
        //dataForSending = new HashMap<>();
        Gson serializer = new Gson();
        jsonsendHolder firstResponse = new jsonsendHolder(gameState.getProperties());//returns this object for serializing but in reality only sends firstResponseSetup.java
        firstResponseSetup trueSend = new firstResponseSetup();
        trueSend.setID(Id);
        System.out.println("Succeeded in creating variables: settings Command");
        firstResponse.setFirstResponseData(trueSend);
        System.out.println("Succeeded in first setting all variables: settings Command");
        
        //following is a funky solution but it is the best way to send one person their necessary information
        try{
        gameState.getClients().get(Id).getBasicRemote().sendText(serializer.toJson(firstResponse));
        }
        catch (IOException E){
            System.err.println(E);
        }
        //peerSpecificIdentifier.sortData(gameState, firstResponse);
        //dataForSending.put(Id, firstResponse); //Normally a method should be used but this is a special command that only 1 person sees
        //System.out.println("Setting return size: "+ dataForSending.size());
        //return dataForSending;//dataForSending init. in interface
    }
    @Override
    public void setUnit(UnitTypes unit){
        //does nothing as no units exist at settings, for now
    }
    /*
    @Override
    public HashMap<String,jsonsendHolder> getJsonsendHolders(){
        return dataForSending;
    }
    */
    @Override
    public String testValue(){
        return "I'm definitly a settingsCommand";
    }
}
