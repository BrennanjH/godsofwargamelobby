/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Faction.Member;
import Faction.Team;

/**
 *
 * @author brenn
 */
public class PlayerData {//holds information related to the player and only the player
    private int money = 3000;//default starting value
    //private final String playerID;
    boolean readyState;
    public Member playerMember;
    public Role playerRole;
    
    public PlayerData(String ID, Role playerRole){
        //playerID = ID;
        playerMember = new Member(ID);
        //Faction name at player joing is their ID
        playerMember.setFactionName(ID);
        this.playerRole = playerRole;
    }

    public Member getPlayerMember() {
        return playerMember;
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
        return playerMember.getId();
    }

    public boolean isReadyState() {
        return readyState;
    }

    public void setReadyState(boolean readyState) {
        this.readyState = readyState;
    }
    
    
}
