/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.matchmake.godsofwargame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author brenn
 */

public class LobbyPointer {
    Map<String, Lobby> gameList = new HashMap<>();
    public boolean needsUpdate = true;
    public ListHolder keys = new ListHolder();
    public void addLobby(Lobby newGame) throws LobbyDiscrepencyException{
        if(gameList.containsKey(newGame.ip)){
            throw new LobbyDiscrepencyException();
        }
        gameList.put(newGame.ip, newGame);
    }
    public void removeLobby(Lobby lostGame) throws LobbyDiscrepencyException{
        if (gameList.containsKey(lostGame.ip)){
            //System.out.println("losing game: "+ lostGame.ip);
            gameList.remove(lostGame.ip);
        }
        else {
            throw new LobbyDiscrepencyException();
        }
    }
    public void asArray(){
        if(needsUpdate){
            //System.out.println("Needs update succ entered");
            needsUpdate = false;
            keys.lobbies = new ArrayList<>();
            for(String s : gameList.keySet()){
                keys.lobbies.add(gameList.get(s));
            }
        }
        //return keys.lobbies;
    }
}
