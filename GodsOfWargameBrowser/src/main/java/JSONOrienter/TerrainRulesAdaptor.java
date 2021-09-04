/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import Units.pathing.GenericTerrainRules;
import Units.pathing.TerrainRules;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author brenn
 */
public class TerrainRulesAdaptor implements JsonDeserializer<TerrainRules>{

    //Just assumes a generic TerrainRules object as objects with more specialized objects will automatically build theirs.
    @Override
    public TerrainRules deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        System.out.println("TerrainRulesAdaptor:  Entered deserialize()");
        
        
        return jdc.deserialize(je, GenericTerrainRules.class);
    }


    
}
