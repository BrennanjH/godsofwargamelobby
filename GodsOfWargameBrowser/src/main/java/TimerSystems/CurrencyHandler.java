/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TimerSystems;

import com.godsofwargame.backend.GodsofWargame;
import com.godsofwargame.backend.PlayerData;

/** Identifies all the criteria for a players income
 *
 * @author brenn
 */
public class CurrencyHandler implements Handling{
    GodsofWargame gameState;
    public CurrencyHandler(GodsofWargame GameState){
        gameState = GameState;
    }
    @Override 
    public void Handle(){
        //loop through all players
        for(String key : gameState.getMapState().getPlayers().keySet()){
            //For convience get player of key
            PlayerData focusedPlayer = gameState.getMapState().getPlayer(key);
            //get Player income Handler
            System.out.println("CurrencyHandler: Handle: playerData owned by: " + focusedPlayer.getPlayerID());
            PlayerIncome generateIncome = new PlayerIncome(focusedPlayer , gameState);
            if(focusedPlayer.playerRole.getRoleType().equals("PlayerRole")){
                //Get player income
                int income = generateIncome.calculatePlayerIncome();
                //Alter Player income
                focusedPlayer.changeMoney(income);
            }
        }
       
    }
}
class PlayerIncome {
    GodsofWargame gameState;
    PlayerData user;
    public PlayerIncome(PlayerData user, GodsofWargame gameState){
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
        return gameState.getCommanders().get(user.getPlayerID()).size() * 10;
    }
    
}
