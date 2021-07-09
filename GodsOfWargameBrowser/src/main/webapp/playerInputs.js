/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


var canvas = document.getElementById("myCanvas");
var context = canvas.getContext("2d");

var hasClicked = false;


var x1,y1,x2,y2;
//TODO add hotkeys and button prompts to manually change event listeners for commands
function placeUnit(event){
    setXYPositions(event);
    setNextUnitPos(x1,y1);
    var newUnit = new createUnitCommand();
    unitCreationPossible(newUnit);

    removeUnitEventListener();
    addMoveEventListener(); //TODO when manual buttons are added remove this
}
function moveClick(event){//TODO create method to check if a unit was selected

    console.log("moveClick called");
    console.log(hasClicked);
    if (hasClicked === false){ //TODO to reduce waste processing perhaps change
        
        hasClicked = true;
        
        x1 = getTrueMousePositionx(event.pageX);
        y1 = getTrueMousePositiony(event.pageY);
        if (unitList[x1][y1][0] === null){
            hasClicked = false;
        }
        else{
        tintSquare(x1,y1);
        }
        
    }
    else{
        hasClicked = false;
        x2 = getTrueMousePositionx(event.pageX);
        y2 = getTrueMousePositiony(event.pageY);
        
        //onClick(x2,y2);//no real need to tint since it will immediatly clear after clicking
        
        movePossibleClient();
        
       
       //fillSpot(y1,x1); //remove tinting if needed but for now keep it
    }
   //console.log(getUnitList());
   
}

function selectSquare(event){//TODO create a display that will show characteristics of pressed tile
    
}

function setXYPositions(event){
    let grid = canvas.getBoundingClientRect();
    
    console.log(event.clientX);
    x1 = getTrueMousePositionx(event.clientX - grid.left);
    y1 = getTrueMousePositiony(event.clientY - grid.top);
    console.log("x: " + x1 + " y: " + y1);
}
function getTrueMousePositionx(x){ //finds the top left corner of pressed square x
   return Math.trunc(x/getImageWidth());
}
function getTrueMousePositiony(y){ //finds the top left corner of pressed squares y
    return Math.trunc(y/getImageHeight());
}

function addMoveEventListener(){
    console.log("addMove");
    canvas.addEventListener("click", moveClick ,false);
}
function removeMoveListener(){
    console.log("removeMove");
    canvas.removeEventListener("click", moveClick,false);
}
function addUnitEventListener(){
    console.log("addUnitEvent");
    canvas.addEventListener("click", placeUnit,false);
}
function removeUnitEventListener(){
    console.log("removeUnitEvent");
    canvas.removeEventListener("click", placeUnit,false);
}
function addselectSquareEventListener(){
    console.log("addSquareEvent");
    canvas.addEventListener("click", selectSquare,false);
}
function removeselectSquareEventListener(){
    console.log("removeSelectSquare");
    canvas.removeEventListener("click", selectSquare, false);
}
function removeAllListeners(){
    console.log("removeAllListeners");
    canvas.removeEventListener("click", selectSquare, false);
    canvas.removeEventListener("click", moveClick,false);
    canvas.removeEventListener("click", placeUnit,false);
}
