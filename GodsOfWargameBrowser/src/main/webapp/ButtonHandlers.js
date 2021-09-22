/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO Find or make actual images for both units and buttons to press down and become
//////////////The Following code is for Tanks/////////////////////////////////////////////////////////////////////////////////////
var hold = false;
function setNextUnit(){

     //setClass("tank");//Currently unused as since input is impossible
     setSpeed(getslideSpeed());
     setHealth(getslideHealth());
     setRange(getslideRange());
     setDamage(getslideDamage());
     removeMoveListener();
     setType(type);
     
     addUnitEventListener();
     console.log("setNextUnitCalled");
 }
/////////////////////The following code is for Tanks//////////////////////////////////////////////////////////////////////////
function handleMDownTank() { //button press for unit
    document.images["tankButton"].src = "wolfmeme.PNG";
  
    return true;
}

function handleMUpTank(){
    resetImages();
    hold = true;
    type = "UnitTank";
    //property = "GROUND"; //really doesn't matter as it is overwritten by java
    //let rules = ["MOUNTAIN"];
    //TerrainRules = new terrainRules(rules);
    document.images["tankButton"].src = "placeholder.png";
  
    return true;
}
function changeImageTank(){
    if(!hold){
        document.images["tankButton"].src= "WolfTemplate.PNG";
    }
    return true;
}
function changeImageBackTank(){
    if(!hold){
        document.images["tankButton"].src = "UnitTank.png";
    }
    return true;
}
/////////////////////The following code is for Planes//////////////////////////////////////////////////////////////////////////
function handleMDownPlane() //button press for unit
{
    document.images["planeButton"].src = "wolfmeme.PNG";
  
  //Note that there are no places a plane can't go so there are no rules here.
    return true;
}

function handleMUpPlane() 
{
    resetImages();
    hold = true;
    type = "UnitPlane";
    //property = "air";
    
    document.images["planeButton"].src= "placeholder.png";
    return true;
}
function changeImagePlane()
{
    if(!hold){
        document.images["planeButton"].src= "WolfTemplate.PNG";
    }
    return true;
}
function changeImageBackPlane()
{
    if(!hold){
        document.images["planeButton"].src = "UnitPlane.png";
    }
    return true;
}
/////////////////////The Following code is for Anti-air//////////////////////////////////////////////////////////////////////////
function handleMDownAA() //button press for unit
{   
    document.images["AAButton"].src = "wolfmeme.PNG";
  
    return true;
}

function handleMUpAA() 
{
    resetImages();
    hold = true;
    type = "UnitAntiAir";
    property = "ground";
    
    //let rules = ["MOUNTAIN"];
    //TerrainRules = new terrainRules(rules);
    document.images["AAButton"].src= "placeholder.png";
    return true;
}
function changeImageAA()
{
    if(!hold){
        document.images["AAButton"].src= "WolfTemplate.PNG";
    }
    return true;
}
function changeImageBackAA()
{
    if(!hold){
        document.images["AAButton"].src = "UnitAntiAir.png";
    }
    return true;
}
/////////////////////The Following Code is for UnitCommandStructures ///////////////////////////////////////////////////////
function handleMDownUCS(){
    document.images["commandUnitButton"].src = "wolfmeme.PNG";
    
    return true;
}
function handleMUpUCS(){
    resetImages();
    hold = true;
    type = "UnitCommandStructure";
    
    //let rules = ["MOUNTAIN"];
    //TerrainRules = new terrainRules(rules);
    document.images["commandUnitButton"].src = "placeholder.png";
    
    return true;
}
function changeImageUCS(){
    if(!hold){
        document.images["commandUnitButton"].src= "WolfTemplate.PNG";
    }
    return true;
}
function changeImageBackUCS(){
    if(!hold){
        document.images["commandUnitButton"].src = "UnitCommandStructure.png";
    }
    return true;
}
//Images need to go back to their original when a new one is pressed.
function resetImages(){
    hold = false;
    changeImageBackTank();
    changeImageBackAA();
    changeImageBackPlane();
    changeImageBackUCS();
}