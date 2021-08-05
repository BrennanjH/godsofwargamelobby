/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Location;

/**This holds theZCoord but it is usually only used for minor logic decisions 
 * so it's best to reference coords as HighLevelCoords
 *
 * @author brenn
 */
public class LowLevelCoord extends HighLevelCoord{
    int zPos;
    
    //It is important that the HighLevel Coords are also filled on construction
    //hence the call to super
    public LowLevelCoord(int zPos, int xPos, int yPos) {
        super(xPos, yPos);
        this.zPos = zPos;
    }

    public int getzPos() {
        return zPos;
    }

    public void setzPos(int zPos) {
        this.zPos = zPos;
    }
    
}
