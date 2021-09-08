/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createUnitCommand(){
    this.header = {
        className : "createUnitCommand",
        unitPresence : true
    };
    this.body = {
        unitObject : new Unit()
    };
}


//Not in use
function setting(){
    
    
    this.header = {
        className : "settingsCommand",
        unitPresence : false
    };
    this.body = {
        //No body necessary for a settings command as it's just a request. 
    };
    
}
//For server
function moveUnitCommand(unit, path){
    this.header = {
        className : "moveUnitCommand",
        unitPresence : true
    };
    this.body = {
        unitObject : unit,
        pathingRoute : path
    };
}
