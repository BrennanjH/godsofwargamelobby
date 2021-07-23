/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global context */

var myimgobj = document.images["jsbutton"];
//var canvas = document.getElementById("myCanvas");
//var context = canvas.getContext("2d");
var arrayHeight;
var arrayWidth;
var width;
var height;
var TerrainMap = new Array();//TODO this array seems to be backwards based on frontend backend cord pos
initGrid();
    


function drawUnit(Unit){
    console.log("drawUnitcalled");
    //console.log(Unit.uzPos);
    if (Unit.uzPos === 0){
        let img = new Image();
        img.src = Unit.UnitType + ".png";
        context.drawImage(img, Unit.uxPos*width, Unit.uyPos*height,width,height );
        console.log(playerID);
        if(Unit.OWNER === playerID){
            
            context.fillStyle = 'rgba(0, 0, 255, 0.4)';
            context.fillRect(Unit.uxPos*width, Unit.uyPos*height,width,height);
        } else {
            context.fillStyle = 'rgba(255, 0, 0, 0.4)';
            context.fillRect(Unit.uxPos*width, Unit.uyPos*height,width,height);
        }
      
    }
}


function tintSquare(x,y){//adds tinting to clicked square
    context.fillStyle = "rgba(255,165,0,.5)";
    context.fillRect(x* width,y * height,width,height);
}


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//getters and setters down yonder
function setArrayHeight(gameHeight){
    arrayHeight = gameHeight;
}
function setArrayWidth(gameWidth){
    arrayWidth = gameWidth;
}
function getHeight(){
    return arrayHeight;
}
function getWidth(){
    return arrayWidth;
}

function setImageWidth() {
    width = canvas.width/arrayWidth;
}
function setImageHeight(){
    height = canvas.height/arrayHeight;
}
function getImageWidth(){
    return width;
}
function getImageHeight(){
    return height;
}
function setTerrainMap(ArrayList){
 
    TerrainMap = ArrayList;
    console.log(TerrainMap);
}
function getTerrainMap(){
    return TerrainMap;
}
function refresh(){
    /*
    for(i=0;i<TerrainMap.length;i++){
        drawTerrain(TerrainMap[i]);
        //console.log(TerrainMap[i]);
    }
        
     */
    for(i=0;i<unitList.length;i++){
        console.log(unitList[i]);
        drawUnit(unitList[i]);
    }
}
function drawTerrain(terrain){
    let unitImage = new Image();
    //console.log(terrain.type.toString() + ".png");
    //console.log(terrain.type + ".png");
    unitImage.src = terrain.type.toString() + ".png";
    //console.log(width);
    context.drawImage(unitImage,(terrain.xVal*width),(terrain.yVal*height) ,width, height);
}