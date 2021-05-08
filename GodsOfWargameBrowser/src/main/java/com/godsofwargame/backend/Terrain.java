/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;

import com.google.gson.annotations.Expose;



public class Terrain{
   //1 is mountains, 0 is plains
   //Change to String so it's more obvious what is what
   @Expose(serialize= true)
   private int type=-1; 
   @Expose(serialize= true)
   private int xVal;
   @Expose(serialize= true)
   private int yVal;
    
    public Terrain(int x,int y){
        generate(x,y);
    }
    private Terrain generate(int x, int y){
      type = (int) (Math.random()*2);
      xVal = x;
      yVal = y;//the render engine will invert these value later due to the grid being based on a screen and not a concept
      //System.out.println(type);
      return this;
    }
    
    public int getxVal() {
        return xVal;
    }

    public void setxVal(int xVal) {
        this.xVal = xVal;
    }

    public int getyVal() {
        return yVal;
    }

    public void setyVal(int yVal) {
        this.yVal = yVal;
    }

    
   
    public void terrainAlteration(int newType){
        type = newType;
    }

    public int getType() {
        return type;
    }
   
}