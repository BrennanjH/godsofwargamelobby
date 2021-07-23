/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;


import JSONOrienter.JSONHandler;
import JSONOrienter.JSONCommandHandler;
import java.io.IOException;
import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author brenn
 */
@ServerEndpoint(value="/godsofwargame", encoders = {TomcatEncoder.class})
public class NewWSEndpoint {
    
    
    private static GodsofWargame gameState = new GodsofWargame();//STATIC
    //private static Set<Session> peers = Collections.synchronizedSet(new HashSet<Session>());
    private playerData player;
    //Gson serial = new Gson();
    //JSONhandler passer = new JSONhandler(); //DEPRECATED
    
    JSONHandler passer = new JSONCommandHandler(gameState);
    
    @OnMessage
    public void messageRecieved(String incoming, Session session) throws IOException, EncodeException {
        System.out.println("incomingJSON: " + incoming);
        System.out.println("incomingID: " + session.getId());
        
        //Note if system starts to recieve to many commands during runtime a buffer for commands or messages may needed
        //Since we can use multicore processing for commands and execution seperatly we should have the buffer store
        //Commands that have been deserialized since no thread contamination can occur at that stage
        CommandProcessor commandSorter = new CommandProcessor();
        commandInterface temp = passer.deserialize(incoming);
        commandSorter.processor(temp, gameState, session.getId());
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
    }
    @OnOpen 
    public void onOpen (Session peer) { 
        //System.out.println(peer.getClass().getName());
        gameState.getClients().put(peer.getId(), peer);
        //System.out.println(peer.getId() + " : This was the ID");
        player = new playerData(peer.getId());
        gameState.getMapState().addPlayer(peer.getId(), player);//place the Id in a hashMap and the players information alongside it
        System.out.println("peer joined: " + peer.getId());
        if (!(gameState.checkState())){    
            System.out.println("Gamestate load started");
            gameState.load();
            System.out.println("Gamestate load Complete");
        }
        //HashMap<String, String> serializedData = new HashMap<>();
        //serializedData = passer.convertToString(peerSpecificIdentifier.sortData(gameState), passer.serialize()); //Note that all players get their gameState refreshed
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
        
    }
    //TODO there is a bug when player leave and join that seems to fail to shutdown or start certain threads (namely Timer0)
    @OnClose
    public void onClose (Session peer) {
        //TODO create new class to store everything in this
        gameState.removeSession(peer);
        if(gameState.getClients().isEmpty()){
            gameState.setIsInstantiated(false);
            gameState.cancelTimer();//Shut down timer at last peer leaving
        }else{
        //HashMap<String, String> serializedData = new HashMap<>();
        //serializedData = passer.convertToString(peerSpecificIdentifier.sortData(gameState), serializedData); //Note that all players get their gameState refreshed
        DataDistributer.distributeToPeers(gameState.getClients(), passer.serialize());
        }
        //peers.remove(peer);
        System.out.println("peer left");
    }
}
