/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faction;

/**
 *
 * @author brenn
 */
public class NoTeamAssociationException extends Throwable{

    /**
     * Creates a new instance of <code>NoTeamAssicatedException</code> without
     * detail message.
     */
    public NoTeamAssociationException() {
    }

    /**
     * Constructs an instance of <code>NoTeamAssicatedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public NoTeamAssociationException(String msg) {
        super(msg);
    }
}
