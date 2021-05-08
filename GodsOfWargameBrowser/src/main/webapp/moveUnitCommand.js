/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function moveUnitCommand(unitObj ,x ,y, z){
    this.className = "moveUnitCommand";
    this.unitType = "UnitTank";
    this.units =  {
        unitObject : unitObj
    };
    this.data = {
        info : {
            newX : x,
            newY : y,
            newZ : z
        }
    };
    
}