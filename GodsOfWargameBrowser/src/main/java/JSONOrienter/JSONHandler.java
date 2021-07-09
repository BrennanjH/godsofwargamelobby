/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

/**
 *
 * @author brenn
 */
public interface JSONHandler {
    //A simple call to GSON is made here but if future changes are made to code
    //GSON serializer might fail to correctly serialize data on its own so this
    //method can be used
    public String serialize(); //TODO this probably will needs params
    //Information has been recieved by server and it is this methods duty to
    //Get it into a shape the server can be happy with
    public void deserialize(String incoming);
}
