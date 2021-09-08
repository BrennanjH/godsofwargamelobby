/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

/**
 *
 * @author brenn
 */
public class playerData {//holds information related to the player and only the player
    private int money;
    private final String playerID;
    transient boolean readyState;
    playerData(String ID){
        playerID = ID;
        money = 20000;//default starting value
    }
    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
    public void changeMoney(int change){
        this.money += change;
    }
    public String getPlayerID() {
        return playerID;
    }

    public boolean isReadyState() {
        return readyState;
    }

    public void setReadyState(boolean readyState) {
        this.readyState = readyState;
    }
    
    
}
