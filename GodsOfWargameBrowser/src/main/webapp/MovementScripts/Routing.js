/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function Routing(){
    this.routing = [];
    
}
function addMove(coordinate){
    Routing.routing.push(coordinate);
}
function createMovementArray(){
    var movement = new Array();
    for ( let i=0;i<TerrainMap.length;i++){
        movement[i] = new Array();
    }
}
function convertTerrainToInt(unit){
    //convert Terrain strings to Ints
    var movement = new Array();
    for ( let i=0;i<arrayWidth;i++){
        movement[i] = new Array();
    }
    for(let i=0;i< TerrainMap.length;i++){
        for(j=0; j<unit.terrainRules.badSpots.length; j++){
            //Set a default value of walkable before deciding if possible
            movement[TerrainMap[i].xVal][TerrainMap[i].yVal] = 0;
            
            if( TerrainMap[i].type === unit.terrainRules.badSpots[j]) {
                //Set movement to 1 to indicate no walkable path
                movement[TerrainMap[i].xVal][TerrainMap[i].yVal] = 1;
                //leave for loop as only 1 badSpot is needed
                break;
            } 
        }
        
    }
    console.log("movement array");
    console.log(movement);
    return movement;
}