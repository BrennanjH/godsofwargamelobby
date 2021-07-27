/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import java.util.HashMap;

/** A class that represents a JSON Header from commands. All GoWG commands should have data stored outside of a body
 * Data stored outside a body is assumed to be header data and can be accessed here. 
 * Header data is represented as a HashMap inside Header that doesn't apply any logic to the data
 *
 * @author brenn
 */
public class JSONHeader {
    public HashMap<String, String> keyValues= new HashMap<>();//Each Key value is a key value for JSON Header, usually 1-3 values fill this map

    //A method to convert the header string to a hashMap which can be used to easily get json values
    private void convertToKeys(){
        
    }

    
}
