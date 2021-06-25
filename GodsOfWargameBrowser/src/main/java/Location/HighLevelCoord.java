/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Location;

/** HighLevel Coords are just the x and y Pos, They named High level because
 * they are values that are always necessary no matter what, More defined coords
 * should extend HighLevel Coord
 *
 * @author brenn
 */
public class HighLevelCoord implements Coordinate{
    int xPos;
    int yPos;

    public HighLevelCoord(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public int getxPos() {
        return xPos;
    }

    @Override
    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    @Override
    public int getyPos() {
        return yPos;
    }

    @Override
    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
    
    
}
