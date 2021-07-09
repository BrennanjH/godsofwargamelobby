/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 var unitList = new Array();
//the following are temp values that are assigned upon button press that get used by constructor
 var classType; //A string unlike the other variables
 var speed;
 var health;
 var range;
 var damage;
 var TerrainRules;
 //following three get assigned when the unit is actually placed onto the grid
 var xPos;
 var yPos;
 var zPos;


 
function Unit() {
    this.UnitType = "UnitTank";//will be set by methods later
    this.terrainRules = TerrainRules;
    this.OWNER = playerID;
        this.uhealth = health;
        this.uspeed = speed;
        this.urange = range;
        this.udamage = damage;
        this.uxPos = xPos;
        this.uyPos = yPos;
        this.uzPos = zPos;//this will also determine which unit is rendered. by default the first unit is chosen. //Currently obsolete

}

 function setNextUnitPos(mouseX,mouseY){
     xPos = mouseX;
     yPos = mouseY;
     
     //setzPos(mouseX,mouseY);
 }
 function updateUnitList(list){
     unitList = list;//Not sure if x,or y comes first
     console.log("updated unitList: ");
     console.log(unitList);
 }
function setClass(setType){
    classType = setType;
}
function setSpeed(setVal){
    speed = setVal;
}
function setHealth(setVal){
    health = setVal;
}
function setRange(setVal){
    range = setVal;
}
function setDamage(setVal){
    damage = setVal;
}
function getClassType(){
    return classType;
}
function getRange(){
    return range;
}
function getHealth(){
    return health;
}
function getDamage(){
    return damage;
}
function getSpeed(){
    return speed;
}
function getUnitList(){
    return unitList;
}
function addUnit(unit){
    unitList[xPos][yPos][zPos] = unit;
}
/*
function setzPos(x,y){//a function which returns the first available slot for a unit in said cord, if no slot is available it will fail
    
    for (i = 0; i<unitList[x][y].length;i++){
        console.log("got this far");
        if (unitList[x][y][i] !== null){
            
            console.log(unitList[x][y][i]);
        }
        else{
            console.log("about 2 set z");
            zPos=i;
            break;
        }
    }
}
 
 */
function getFirstAvailableUnitList(x,y){
    for(i =0; i<5;i++){
        if(unitList[x][y][i] === null){
            return i;
        }
    }
    return "undefined";
}