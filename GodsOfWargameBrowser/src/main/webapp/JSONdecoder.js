/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function JSONgameStateUpdater(json){//Properly updates gamestate (lists of units and terrain) for client
    var message = JSON.parse(json);
    console.log("entered into JSONLOGICFINDER");
    turnOff(message.firstResponseData);
    terrainHandling(message.terrainList);// Unlike UnitHandling which runs regardless of Unit presence TerrainHandling doesn't run if empty
    unitHandling(message.unitList);
    playerDataHandling(message.playerInfo);
}
function playerDataHandling(data){
    if(typeof data !== "undefined"){
        money = data.money;
        document.getElementById("money").textContent = money;
    }
}
function serverMessageHandling(message){
    console.log(message);
}

function initGrid(){
        setArrayHeight(10);
        setArrayWidth(10);
        setImageWidth();
        setImageHeight();
        addMoveEventListener();
}

function unitHandling(list){
    console.log(list);
    console.log("insideUnitHandling");
    updateUnitList(list);//look in UnitObject.js for related methods and variables
    
}
function terrainHandling(list){
    if(typeof list[0] !== "undefined"){
        setTerrainMap(list);//TODO change this to only change the terrain objects that conflict with new Terrain (may not be reasonable design choice)
        for(i =0; i< list.length; i++){
            drawTerrain(list[i]);
        }
        
    }
}
//TODO see if there is a way to cut these methods out after first use
function turnOff(message){//this function quickly becomes 
    if(typeof message !== "undefined"){
        
            setPlayerId(message.ID);
            applySettingsData(message.settings);
            

        function setPlayerId(Id){//afterSettingsCommand is executed the ID should return along with SettingsData
            if(typeof Id !== "undefined"){
                setID(Id);
            }
        }   
        function applySettingsData(settings){
            if(typeof settings !== "undefined"){

            }
        }
    }
}