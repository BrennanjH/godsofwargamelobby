/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faction;

import java.util.ArrayList;
import java.util.List;

/**A Coalition object represents a list of members that are related together
 *
 * @author brenn
 */
public class Coalition implements Team{
    private List<Member> coalitionMembers;
    String teamName;
    public Coalition(String name){
        coalitionMembers = new ArrayList<>();
        teamName = name;
    }
    @Override
    public String getName(){
        return teamName;
    }

    @Override
    public void addTeamMember(Member newMember) {
        //validate if user already exists in team
        for(Member m : coalitionMembers) {
            if (newMember.getId().equals(m.getId())){
                //User already exists so no change is needed
                return;
            }
        }
        coalitionMembers.add(newMember);
    }

    @Override
    public void removeTeamMember(String memberId) {
        for(Member m : coalitionMembers) {
            if (memberId.equals(m.getId())){
                coalitionMembers.remove(m);
                return;
            }
        }
    }

    @Override
    public List<Member> getTeam() {
        return coalitionMembers;
    }
}
