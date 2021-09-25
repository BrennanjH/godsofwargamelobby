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
    propertiesHandling(message.properties);
    territoryHandling(message.territories);
}
function territoryHandling(data){
    if(Object.entries(data).length !== 0){
        territoryMap = data;
    }
}
function playerDataHandling(data){
    if(Object.entries(data).length !== 0){
        money = data.money;
        document.getElementById("money").textContent = money;
    }
}
function serverMessageHandling(message){
    console.log(message);
}

function initGrid(){
    setArrayHeight(gameSettings.cols);
    setArrayWidth(gameSettings.rows);
    setImageWidth();
    setImageHeight();
    addMoveEventListener();
}

function unitHandling(list){
    updateUnitList(list);//look in UnitObject.js for related methods and variables
}
function terrainHandling(list){
    if(Object.entries(list).length !== 0){
        console.log("Setting TerrainList");
        //console.log(list);
        setTerrainMap(list);//TODO change this to only change the terrain objects that conflict with new Terrain (may not be reasonable design choice)
        
        /*
        for(i =0; i< list.length; i++){
            drawTerrain(list[i]);
        }
        */
    }
}
function propertiesHandling(properties){
    if(Object.entries(properties).length !== 0){
        gameSettings = properties;
    }
}
//TODO see if there is a way to cut these methods out after first use
function turnOff(message){//this function quickly becomes 
    if(Object.entries(message).length !== 0){
            console.log("Setting first response data");
            console.log(typeof message);
            
            setPlayerId(message.ID);
            
            setTeamName(playerID);

        function setPlayerId(Id){//afterSettingsCommand is executed the ID should return along with SettingsData
            if(typeof Id !== "undefined"){
                setID(Id);
            }
        }   
        
    }
}