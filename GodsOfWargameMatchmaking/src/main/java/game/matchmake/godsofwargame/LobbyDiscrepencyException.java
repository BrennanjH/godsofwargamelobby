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

public class LobbyDiscrepencyException extends Exception{

    /**
     * Creates a new instance of <code>LobbyDiscrepency</code> without detail
     * message.
     */
    public LobbyDiscrepencyException() {
    }

    /**
     * Constructs an instance of <code>LobbyDiscrepency</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public LobbyDiscrepencyException(String msg) {
        super(msg);
    }
}
