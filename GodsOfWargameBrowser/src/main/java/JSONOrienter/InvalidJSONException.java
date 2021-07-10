/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

/** The JSON recieved by backend is not capable of being interpreted safely Or has
 * been tampered with
 *
 * @author brenn
 */
public class InvalidJSONException extends Exception{

    /**
     * Creates a new instance of <code>invalidJSON</code> without detail
     * message.
     */
    public InvalidJSONException() {
    }

    /**
     * Constructs an instance of <code>invalidJSON</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public InvalidJSONException(String msg) {
        super(msg);
    }
}
