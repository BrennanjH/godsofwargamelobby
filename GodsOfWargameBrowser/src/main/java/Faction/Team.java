/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Faction;

import java.util.List;

/**
 *
 * @author brenn
 */
public interface Team {
    public String getName();
    public void addTeamMember(Member newMember);
    public void removeTeamMember(String memberId);
    public List<Member> getTeam();
    
}
