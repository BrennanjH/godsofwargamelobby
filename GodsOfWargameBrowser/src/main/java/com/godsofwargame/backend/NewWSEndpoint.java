/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;


import JSONOrienter.JSONHandler;
import JSONOrienter.CommandHandler;
import com.godsofwargame.commands.GameEndHandler;
import com.godsofwargame.commands.JoinCoalitionCommand;
import com.godsofwargame.commands.commandInterface;
import com.godsofwargame.commands.internalCommands;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author brenn
 */
@ServerEndpoint(value="/godsofwargame", encoders = {TomcatEncoder.class})
public class NewWSEndpoint {
    
    //Used to perform a preload on the gameState, This is kept seperate from onopen since it isn't directly related to users
    public NewWSEndpoint(){
        //System.out.println("Can you believe this worked?: " + Math.random());
    }
    //TODO Create Syncronized() on gameState so that only one thread can access it
    private static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(GOWConfig.class);
    private static GodsofWargame gameState = ctx.getBean("godsOfWargame", GodsofWargame.class);//WARNING might need to become Static
    
    private PlayerData player;
    
    JSONHandler passer = new CommandHandler(gameState);
    
    @OnMessage
    public void messageRecieved(String incoming, Session session) throws IOException, EncodeException {
        System.out.println("incomingJSON: " + incoming);
        System.out.println("incomingID: " + session.getId());
        
        //Note if system starts to recieve to many commands during runtime a buffer for commands or messages may needed
        //Since we can use multicore processing for commands and execution seperatly we should have the buffer store
        //Commands that have been deserialized since no thread contamination can occur at that stage
        
        //Get Command from incoming
        commandInterface temp = passer.deserialize(incoming);
        
        //Use command object to perform buisness logic
        temp.execute(gameState,session.getId());
        
        //Update user on gamestate changes caused by command
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
    }
    @OnOpen 
    public void onOpen (Session peer) {
        
        System.out.println("peer joined: " + peer.getId());
        //Perform first time setup when user joins
        if (!(gameState.getReadyStates().isPreLoad())){    
            System.out.println("Gamestate pre-load started");
            
            gameState.preload();
            System.out.println("Gamestate pre-load Complete");
        } else if( (gameState.getReadyStates().isFullyLoaded()) ) {
            
            try {
                System.out.println("NewWSEndpoint: onOpen: game has started peer is being closed");
                peer.close(); 
                //TODO change .close to use a close reason
            } catch (IOException ex) {
                Logger.getLogger(NewWSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
            return;
        }
        
        //add reference to new user into GodsofWargame Object
        gameState.getClients().put(peer.getId(), peer);
        player = new PlayerData(peer.getId());
        
        //Send user settings data
        commandInterface setting = new settingsCommand();
        setting.execute(gameState, peer.getId());
        
        
        gameState.getMapState().addPlayer(peer.getId(), player);//place the Id in a hashMap and the players information alongside it
        
        //Add player to new Team named after their Id
        
        JoinCoalitionCommand teamJoiner = new JoinCoalitionCommand();
        teamJoiner.setFactionName(peer.getId());
        teamJoiner.execute(gameState, peer.getId());
        //Update new user with new gamestate
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
        
    }
    //TODO there is a bug when player leave and join that seems to fail to shutdown or start certain threads (namely Timer0), (I think I fixed this)
    @OnClose
    public void onClose (Session peer) {
        //TODO a lot of objects need to be removed still; Territory, and teams?
        
        GameEndHandler gameEnder = new GameEndHandler(peer.getId(), gameState);
        System.out.println("NewWSEndpoint: onClose: peer is closed?: " + peer.isOpen());
        gameEnder.playerLost();
        /*
        if (peer.isOpen()){
            try {
                gameState.removeSession(peer);
                DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
            } catch (IOException ex) {
                Logger.getLogger(NewWSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                gameState.removeSession(peer);
            } catch (IOException ex) {
                Logger.getLogger(NewWSEndpoint.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        */
            //System.out.println("onClose: " + gameState.getClients().isEmpty());
        if(gameState.getClients().isEmpty()){
            ctx.close();
            //ShutDown timers if any are started
            if(gameState.getReadyStates().isFullyLoaded())
                gameState.cancelTimer();//Shut down timer at last peer leaving
            //reset the start so new players can join
            gameState.resetReadyState();
            
        }
        //peers.remove(peer);
        System.out.println("peer left");
        
        }
    
}
