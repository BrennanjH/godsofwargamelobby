/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.commandInterface;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
//import com.google.gson.JsonSerializationContext;
//import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

@Deprecated
public class InterfaceAdapter implements JsonDeserializer<commandInterface>{
    
    private static final String CLASSNAME = "className";//the key pertaining to command names
    //the key for all information not pertaining to command info
    private static final String DATA = "data";
    private static final String INFO = "info";
    
    @Override
    public commandInterface deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        System.out.println(" inside deserialize commandInterface");
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive prim = (JsonPrimitive) jsonObject.get(CLASSNAME);
        jsonObject = jsonObject.getAsJsonObject(DATA);
        //System.out.println(prim.getAsString());
        //System.out.println(jsonObject.size());
        String className = prim.getAsString();
        Class klass = getObjectClass(className);
        
        
            
        
        return jsonDeserializationContext.deserialize(jsonObject.get(INFO), klass);//WARNING bug here since .get can't actually see past DATA
            
        }
    /*
    @Override
        public <commandInterface> JsonElement serialize(<commandInterface> jsonElement, Type type, JsonSerializationContext jsonSerializationContext) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty(CLASSNAME, jsonElement.getClass().getName());
            jsonObject.add(DATA, jsonSerializationContext.serialize(jsonElement));
            return jsonObject;
        }
    */
    /****** Helper method to get the className of the object to be deserialized
     * @param className
     * @return  *****/
        public Class getObjectClass(String className) {
            try {
                //System.out.println("getObjectCalled");
                return Class.forName("com.godsofwargame.backend." + className);//TODO what is .forName(string) doing?
                } catch (ClassNotFoundException e) {
                    //e.printStackTrace();
                    throw new JsonParseException(e.getMessage());
                }
        }
}

                      