/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function createUnitCommand(){
    this.className = "createUnitCommand";
    this.unitType = "UnitTank";
    this.units = {
        unitObject : new Unit()//WARNING This severely limits command variation.
    };
    this.data = {
        info : {
            notReal : 0
        }
    };
}
