/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.matchmake.godsofwargame;

/**
 *
 * @author brenn
 */
public class Lobby {
    String ip;
    
    int players;
    boolean password = false;
    @Override
    public String toString(){
        return ip + ":serverAddress;" + players + ":playerCount;" + password + ":passwordPresence";
    }
    
}
