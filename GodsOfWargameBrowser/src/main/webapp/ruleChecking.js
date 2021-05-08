/* 
    Methods in this javascript file send Unit information for server side confirmation after doing client side check
 */
function unitCreationPossible(unit){
    
    console.log("unit creation possible entered");
    sendText(JSON.stringify(unit));
}
function movePossibleServer(command){//Sends information to Server for processing move commands
    
    sendText(JSON.stringify(command));
  
}
function movePossibleClient(){//runs first then sends a check to server
    let zpos = getFirstAvailableUnitList(x2,y2);
    console.log(zpos + " z pos");
    if((zpos === "undefined" || checkTerrainType(y2,x2) >= 1)){
        console.log("movement error");
    }
    else{
        console.log(zpos);
        console.log(unitList[x1][y1][0]);
        var movingunit = new moveUnitCommand(unitList[x1][y1][0]  ,  x2  , y2, zpos);//moves the top unit only
        
        movePossibleServer(movingunit);
        
    }
}
function isUnitPresent(x,y,z){//checks to see if a unit exists at said cord
    
    return false;
}
function checkTerrainType(x,y){
    return TerrainMap[x][y].type;
}
