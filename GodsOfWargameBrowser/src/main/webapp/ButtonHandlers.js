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
     
     addUnitEventListener();
     console.log("setNextUnitCalled");
 }

function handleMDownTank() //button press for unit
{
  document.images["jsbutton"].src = "wolfmeme.PNG";
  
  return true;
}

function handleMUpTank() 
{
    resetImages();
    hold = true;
    
    let rules = ["MOUNTAIN"];
    TerrainRules = new terrainRules(rules);
    document.images["jsbutton"].src = "placeholder.png";
  
  return true;
}
function changeImageTank()
{
    if(!hold){
        document.images["jsbutton"].src= "WolfTemplate.PNG";
    }
    return true;
}
function changeImageBackTank()
{
    if(!hold){
        document.images["jsbutton"].src = "UnitTank.png";
    }
 return true;
}
/////////////////////The following code is for Planes//////////////////////////////////////////////////////////////////////////
function handleMDownPlane() //button press for unit
{
  document.images["jsbutton"].src = "wolfmeme.PNG";
  
  //Note that there are no places a plane can't go so there are no rules here.
  return true;
}

function handleMUpPlane() 
{
  resetImages();
  hold = true;
  
  document.images["jsbutton"].src= "placeholder.png";
  return true;
}
function changeImagePlane()
{
    if(!hold){
        document.images["jsbutton"].src= "WolfTemplate.PNG";
    }
return true;
}
function changeImageBackPlane()
{
    if(!hold){
        document.images["jsbutton"].src = "UnitPlane.png";
    }
 return true;
}
/////////////////////The Following code is for Anti-air//////////////////////////////////////////////////////////////////////////
function handleMDownAA() //button press for unit
{   
    document.images["jsbutton"].src = "wolfmeme.PNG";
  
  return true;
}

function handleMUpAA() 
{
    resetImages();
    hold = true;
    
    let rules = ["MOUNTAIN"];
    TerrainRules = new terrainRules(rules);
    document.images["jsbutton"].src= "placeholder.png";
    return true;
}
function changeImageAA()
{
    if(!hold){
        document.images["jsbutton"].src= "WolfTemplate.PNG";
    }
return true;
}
function changeImageBackAA()
{
    if(!hold){
        document.images["jsbutton"].src = "UnitAntiAir.png";
    }
 return true;
}
//Images need to go back to their original when a new one is pressed.
function resetImages(){
    hold = false;
    changeImageBackTank();
    changeImageBackAA();
    changeImageBackPlane();
}