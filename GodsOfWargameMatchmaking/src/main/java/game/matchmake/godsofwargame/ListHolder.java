/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.matchmake.godsofwargame;

import java.util.ArrayList;

/**
 *A Wrapper class to hold lists that way gson will serialize the list with a name
 * @author brenn
 */
public class ListHolder {
    public ArrayList<Lobby> lobbies = new ArrayList<>();
}
