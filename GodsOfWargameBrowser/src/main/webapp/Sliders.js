/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var speedSlide = document.getElementById("SpeedSlider");
var speedPut = document.getElementById("speedVal");

var rangeSlide = document.getElementById("RangeSlider");
var rangePut = document.getElementById("rangeVal");

var healthSlide = document.getElementById("HealthSlider");
var healthPut = document.getElementById("healthVal");

var damageSlide = document.getElementById("DamageSlider");
var damagePut = document.getElementById("damageVal");

speedPut.innerHTML = speedSlide.value;
rangePut.innerHTML = rangeSlide.value;
healthPut.innerHTML = healthSlide.value;
damagePut.innerHTML = damageSlide.value;

rangeSlide.oninput = function() {
  rangePut.innerHTML = this.value;
};

speedSlide.oninput = function() {
  speedPut.innerHTML = this.value;
};

healthSlide.oninput = function() {
  healthPut.innerHTML = this.value;
};

damageSlide.oninput = function() {
  damagePut.innerHTML = this.value;
};

function getslideDamage(){
    return damageSlide.value;
}
function getslideHealth(){
    return healthSlide.value;
}
function getslideRange(){
    return rangeSlide.value;
}
function getslideSpeed(){
    return speedSlide.value;
}