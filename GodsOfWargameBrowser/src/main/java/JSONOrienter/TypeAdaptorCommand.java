/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.commands.commandInterface;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/** Custom deserializer that is passed the commands Type and then parses
 * the json into a command as well as access other TypeAdaptors for Units
 *
 * @author brenn
 */
public class TypeAdaptorCommand implements JsonDeserializer<commandInterface>{
    final String TYPE;
    public TypeAdaptorCommand(String type){
        System.out.println(" inside TypeAdaptorCommand constructor");
        TYPE = type;
    }

    @Override
    public commandInterface deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        System.out.println(" inside TypeAdaptorCommand");
        JsonObject jsonObject = je.getAsJsonObject();
        Class klass = getObjectClass(TYPE);
        
        
        return jdc.deserialize(jsonObject, klass);
        
    }
    
    public Class getObjectClass(String className) {
            try {
                //System.out.println("getObjectCalled");
                return Class.forName("com.godsofwargame.commands." + className);//TODO what is .forName(string) doing?
                } catch (ClassNotFoundException e) {
                    //e.printStackTrace();
                    throw new JsonParseException(e.getMessage());
                }
    }
}
