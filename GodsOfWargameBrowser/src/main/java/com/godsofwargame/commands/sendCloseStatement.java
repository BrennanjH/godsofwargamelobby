/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.jsonsendHolder;
import com.godsofwargame.backend.peerSpecificIdentifier;
import java.util.HashMap;
import javax.websocket.Session;

/**
 *  Command sends A close message to all sessions. Should only be accessible by commands that handle gameEnding scenarios
 * @author brenn
 */
//TODO should use a generic to allow methods to except either jsonsendHolder or A String
class sendCloseStatement {
    GodsofWargame gameState;
    sendCloseStatement(GodsofWargame gameState){
        this.gameState = gameState;
    }
    //Sends default endMessage, Doesn't identify who won or what happened and is intended for Emergency shutdown.
    HashMap<String, jsonsendHolder> sendCloseMessage(){
        jsonsendHolder messageHolder = new jsonsendHolder();
        messageHolder.addServerMessage("GAME HAS ENDED");
        return peerSpecificIdentifier.simpleData(gameState, messageHolder);
        
    }
    //Broadcasts a custom message to everybody, Intended for use cases where custom Error is desired
    HashMap<String, jsonsendHolder> sendCustomCloseMessage(String messageEnd){
        jsonsendHolder messageHolder = new jsonsendHolder();
        messageHolder.addServerMessage(messageEnd);
        return peerSpecificIdentifier.simpleData(gameState, messageHolder);
        
    }
    //Sends just one session a close message, for use when somebody is Forcibly being disconnected but other users aren't
    void sendSessionCloseMessage(String message, Session listener){
        jsonsendHolder messageHolder = new jsonsendHolder();
        
    }
}
