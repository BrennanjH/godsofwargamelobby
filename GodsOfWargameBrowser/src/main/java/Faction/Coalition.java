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
    List<Member> coalitionMembers;
    String teamName;
    public Coalition(String name){
        coalitionMembers = new ArrayList<>();
        teamName = name;
    }
    @Override
    public String getName(){
        return teamName;
    }
}
