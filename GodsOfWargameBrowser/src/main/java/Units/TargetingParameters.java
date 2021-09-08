/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units;

/**
 *
 * @author brenn
 */
public class TargetingParameters {
    public TargetingParameters(String[] interactable){
        interactions = interactable;
    }
    String[] interactions;
    public boolean canShootProperty(String property){
        for(String interaction : interactions) {
            if(interaction.equals(property)){
                return true;
            }
        }
        return false;
    }
   
}
