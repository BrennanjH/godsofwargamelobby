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
public class Member {
    public Member(String Id){
        this.Id = Id;
    }
    //The Id is gotten from session ID and is simply a way to represents players
    //in terms of factions
    private String Id;

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }
    
    
}
