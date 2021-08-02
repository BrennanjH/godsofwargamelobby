/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import com.godsofwargame.backend.UnitTypes;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author brenn
 */
public class TypeAdaptorUnit implements JsonDeserializer<UnitTypes>{
    String TYPE;
    //Unlike commands which can conviently get their type from the header Units store their type internally,
    //So at creation we must get the unitType from the imported Unit.
    public TypeAdaptorUnit(JsonElement insert) {
        //System.out.println(" inside TypeAdaptorUnit Constructor");
        //System.out.println(insert.getAsJsonObject().entrySet().toString());
        TYPE = insert.getAsJsonObject().get("UnitType").getAsJsonPrimitive().getAsString(); //references UnitTypes String value titled UnitTypes
    }

    @Override
    public UnitTypes deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        System.out.println(" inside TypeAdaptorUnit");
        JsonObject jsonObject = je.getAsJsonObject();
        Class klass = getObjectClass(TYPE);
        //System.out.println("test: " + klass.getTypeName());
        return jdc.deserialize(jsonObject, klass);
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
