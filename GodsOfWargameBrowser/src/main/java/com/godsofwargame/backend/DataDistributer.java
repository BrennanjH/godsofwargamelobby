/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.Session;

/**
 *This class holds necessary methods to properly relay server messages to it's peers.
 * This class does not handle information formatting, it's a top level handler
 * @author brenn
 */
public class DataDistributer {
    
    public static void distributeToPeers(HashMap<String, Session> peers, HashMap<String,String> data){//Sends the JsonArray with each players respective information to the players.
        
        try {
            //Set<String> keys = data.keySet();
            for (String s : data.keySet()){
                System.out.println("Talking to: " + s);
                System.out.println("Sending them: " + data.get(s));
                Session temp = peers.get(s);
                temp.getBasicRemote().sendObject(data.get(s));
            }
                
            
        } catch (IOException | EncodeException ex) {
            Logger.getLogger(DataDistributer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

}
