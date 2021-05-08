/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Units.pathing;

/**
 *
 * @author brenn
 */
public interface PathLogic {
    public Routing calcPath(int startX,int startY, int endX, int endY);
    public void cardinalScanner();
    public void cardinalScanner(int lastX, int lastY);
}
