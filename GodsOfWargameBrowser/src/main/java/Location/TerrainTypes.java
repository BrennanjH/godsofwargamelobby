package Location;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/** A class that represents all types of terrain the system can use.
 *
 * @author brenn
 */
public class TerrainTypes { //TODO replace with properties file and use spring beans to create terrain
    static private String[] temp = {"PLAIN","MOUNTAIN"};

    public static String[] getTemp() {
        return temp;
    }
    
}
