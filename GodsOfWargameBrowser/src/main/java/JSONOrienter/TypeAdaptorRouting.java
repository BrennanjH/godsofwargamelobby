/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSONOrienter;

import Units.pathing.Routing;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/** 
 *
 * @author brenn
 */
public class TypeAdaptorRouting implements JsonDeserializer<Routing> {

    @Override
    public Routing deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        //System.out.println("TypeAdaptorRouting: This is je: " + je.getAsJsonObject().entrySet().toString());
        //System.out.println(je.getAsString());
        Gson des = new Gson();
        Routing test = new Routing();
        test.setPathingRoute(des.fromJson(je, int[][].class));
        return test;
        //return test.setPathingRoute(des.fromJson(je, int[][].class));
        
        //return jdc.deserialize(je, Routing.class);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
