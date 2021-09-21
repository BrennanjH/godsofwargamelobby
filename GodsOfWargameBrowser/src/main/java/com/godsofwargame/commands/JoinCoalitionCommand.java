/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.commands;

import Faction.Coalition;
import Faction.Team;
import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.PlayerData;
import com.godsofwargame.backend.UnitTypes;
import java.util.ArrayList;

/**
 *
 * @author brenn
 */
public class JoinCoalitionCommand implements commandInterface {
    private String factionName;
    transient private GodsofWargame gameState;
    @Override
    public void execute(GodsofWargame gameState, String Id) {
        //Check if gameState has fully loaded so teams can't change
        if(!gameState.getReadyStates().isFullyLoaded())  {  
            this.gameState = gameState;
            boolean teamExists = false;
            PlayerData talkingMember = gameState.getMapState().getPlayer(Id);
            //check if server has requested team
            ArrayList<Team> removeTeam = new ArrayList<>();
            for (Team t : gameState.getFactions()) { 
                
                if ( t.getName().equals(factionName)) {
                    //Add member to the team list
                    
                    t.addTeamMember(talkingMember.getPlayerMember());
                    teamExists = true;
                }
                //remove player from all teams they were in before
                t.removeTeamMember(Id);
                
                //Tag empty factions for removal at the end
                if(t.getTeam().isEmpty()){
                    removeTeam.add(t);
                }
            }
            
            
            //Create a new team if team didn't exist
            if (!teamExists) {
                Coalition newCoalition = new Coalition(factionName);
                newCoalition.addTeamMember(talkingMember.getPlayerMember());
                gameState.getFactions().add(newCoalition);
            }
            //remove empty factions
            for(Team t : removeTeam){
                gameState.getFactions().remove(t);
            }
        }
        System.out.println("JoinCoalitionCommand: Execute: Number of teams: " + gameState.getFactions().size());
    }

    @Override
    public void setUnit(UnitTypes unit) {
        //Nothing should be done as units aren't used by this command
    }

    @Override
    public String testValue() {
       return "This is a JoinCoalitionCommand"; //To change body of generated methods, choose Tools | Templates.
    }
    
}
