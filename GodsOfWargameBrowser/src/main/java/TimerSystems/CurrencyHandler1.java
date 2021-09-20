/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimerSystems;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.PlayerData1;

/** Identifies all the criteria for a players income
 *
 * @author brenn
 */
public class CurrencyHandler1 implements Handling{
    GodsofWargame gameState;
    public CurrencyHandler1(GodsofWargame GameState){
        gameState = GameState;
    }
    @Override 
    public void Handle(){
        //loop through all players
        for(String key : gameState.getMapState().getPlayers().keySet()){
            //For convience get player of key
            PlayerData1 focusedPlayer = gameState.getMapState().getPlayer(key);
            //get Player income Handler
            PlayerIncome generateIncome = new PlayerIncome(focusedPlayer , gameState);
            //Get player income
            int income = generateIncome.calculatePlayerIncome();
            //Alter Player income
            focusedPlayer.changeMoney(income);
        }
       
    }
}
class PlayerIncome {
    GodsofWargame gameState;
    PlayerData1 user;
    public PlayerIncome(PlayerData1 user, GodsofWargame gameState){
        this.gameState = gameState;
        this.user = user;
    }
    public int calculatePlayerIncome(){
        //Identify # of commandUnits
        int commandUnitIncome = calculateCommandUnitIncome();
        //Identify amount of controlled Territory
        int territorySizeIncome = 0; //Not implemented
        //Add Ammounts for each criteria
        return commandUnitIncome + territorySizeIncome;
    }
    private int calculateCommandUnitIncome(){
        return gameState.getCommanders().get(user.getPlayerID()).size() * 5;
    }
    
}
