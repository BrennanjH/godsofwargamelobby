/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Location;

import com.godsofwargame.backend.TerrainTypes;



public class Terrain{
   //@Expose(serialize=false)
   //private String[] temp = {"PLAIN","MOUNTAIN"};
   
   //@Expose(serialize= true)
   private String type="NOT_SET"; 
   //@Expose(serialize= true)
   private int xVal; //TODO change the xVal and yVal into Coordinate Objects
   //@Expose(serialize= true)
   private int yVal;
    
    public Terrain(int x,int y){
        generate(x,y);
    }
    private Terrain generate(int x, int y){
      type = TerrainTypes.getTemp()[((int) (Math.random()*2))];
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

    public String getType() {
        return type;
    }

    public void terrainAlteration(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    
   
}