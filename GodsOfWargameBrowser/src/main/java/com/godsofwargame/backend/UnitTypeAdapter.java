/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.godsofwargame.backend;


import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import java.lang.reflect.Type;
/**
 *
 * @author brenn
 */
public class UnitTypeAdapter implements JsonDeserializer<UnitTypes>{ //may break if array is used in future, should probably not change though
    private static final String UNITSTORAGE = "units";
    //private static final String DATA = "data";
    private static final String TYPE = "unitType";
    private static final String UNIT = "unitObject";
            
    @Override
    public UnitTypes deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        System.out.println(" inside deserialize UnitTypes");
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        //jsonObject = jsonObject.getAsJsonObject(UNIT); //WARNING this might not be needed and in fact might cause prim trouble getting data

        JsonPrimitive prim = jsonObject.getAsJsonPrimitive(TYPE);
        System.out.println(prim.getAsString());
        String className = prim.getAsString();
        Class klass = getObjectClass(className);
        jsonObject = jsonObject.getAsJsonObject(UNITSTORAGE);
        return jsonDeserializationContext.deserialize(jsonObject.get(UNIT), klass);
        
    }
    
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
