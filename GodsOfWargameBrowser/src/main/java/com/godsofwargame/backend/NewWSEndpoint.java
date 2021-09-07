/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;


import JSONOrienter.JSONHandler;
import JSONOrienter.CommandHandler;
import com.godsofwargame.commands.commandInterface;
import java.io.IOException;
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
    //TODO Create Syncronized() on gameState so that only one thread can access it
    private static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(GOWConfig.class);
    private static GodsofWargame gameState = ctx.getBean("godsOfWargame", GodsofWargame.class);//WARNING might need to become Static
    //private static GodsofWargame gameState = new GodsofWargame();
    
    //private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private playerData player;
    //Gson serial = new Gson();
    //JSONhandler passer = new JSONhandler(); //DEPRECATED
    
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
        //add reference to new user into GodsofWargame Object
        gameState.getClients().put(peer.getId(), peer);
        player = new playerData(peer.getId());
        
        commandInterface setting = new settingsCommand();
        setting.execute(gameState, peer.getId());
        
        gameState.getMapState().addPlayer(peer.getId(), player);//place the Id in a hashMap and the players information alongside it
        System.out.println("peer joined: " + peer.getId());
        //Perform first time setup when user joins
        if (!(gameState.checkState())){    
            System.out.println("Gamestate load started");
            gameState.load();
            System.out.println("Gamestate load Complete");
        }
        
        //Update new user with new gamestate
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
        
    }
    //TODO there is a bug when player leave and join that seems to fail to shutdown or start certain threads (namely Timer0), (I think I fixed this)
    @OnClose
    public void onClose (Session peer) {
        gameState.removeSession(peer);
        if(gameState.getClients().isEmpty()){
            ctx.close();
            gameState.setIsInstantiated(false);
            gameState.cancelTimer();//Shut down timer at last peer leaving
        }else{
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
        }
        //peers.remove(peer);
        System.out.println("peer left");
    }
}
