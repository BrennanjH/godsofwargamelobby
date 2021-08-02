/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**A class that represent the JSON header and body as two Strings usually converted to Message Type
 *
 * @author brenn
 */
public class SeperatedSimpleMessage {
    private final String BODY = "body";
    private final String HEADER = "header";
            
    JsonElement body;
    JsonElement header;
    public SeperatedSimpleMessage(JsonObject message){
        body = message.get(BODY);
        header = message.get(HEADER);
    }
}
