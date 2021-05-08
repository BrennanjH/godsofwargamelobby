/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//TODO Find or make actual images for both units and buttons to press down and become
//////////////The Following code is for Tanks/////////////////////////////////////////////////////////////////////////////////////
function handleMDownTank() //button press for unit
{
  document.images["jsbutton"].src = "wolfmeme.PNG";
  
  return true;
}

function handleMUpTank() 
{
  
  changeImageTank();
  return true;
}
function changeImageTank()
{
document.images["jsbutton"].src= "WolfTemplate.PNG";
return true;
}
function changeImageBackTank()
{
 document.images["jsbutton"].src = "UnitTank.png";
 return true;
}
/////////////////////The following code is for Planes//////////////////////////////////////////////////////////////////////////
function handleMDownPlane() //button press for unit
{
  document.images["jsbutton"].src = "wolfmeme.PNG";
  
  return true;
}

function handleMUpPlane() 
{
  
  changeImagePlane();
  return true;
}
function changeImagePlane()
{
document.images["jsbutton"].src= "WolfTemplate.PNG";
return true;
}
function changeImageBackPlane()
{
 document.images["jsbutton"].src = "UnitTank.png";
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
  
  changeImageAA();
  return true;
}
function changeImageAA()
{
document.images["jsbutton"].src= "WolfTemplate.PNG";
return true;
}
function changeImageBackAA()
{
 document.images["jsbutton"].src = "UnitTank.png";
 return true;
}