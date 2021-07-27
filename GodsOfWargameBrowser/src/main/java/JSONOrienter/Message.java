/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 *
 * @author brenn
 */
public class Message implements JSONFormat {
    JSONHeader fhead;
    JSONBody fBody;
    public Message(SeperatedSimpleMessage convert){
        toHeader(convert.header);
    }
    @Override
    public JSONBody getBody() {
        return fBody;
    }
    @Override
    public JSONHeader getHead() {
        return fhead;
    }
    private void toHeader(String head){
        JsonElement jelement = new JsonPrimitive(head);
        
    }
    private void toBody(){
        
    }
}
