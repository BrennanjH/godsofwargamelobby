/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import Faction.Team;
import java.util.List;

/** A class that represents information about the servers current state, 
 *
 * @author brenn
 */
public class BtFMetaData {
    List<Team> teamList;
    public BtFMetaData(GodsofWargame gameState){
        teamList = gameState.getFactions();
    }
}
