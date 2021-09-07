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
import java.util.ArrayList;

/** 
 *
 * @author brenn
 */
public class TypeAdaptorRouting implements JsonDeserializer<Routing> {

    @Override
    public Routing deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        
        Gson des = new Gson();
        Routing test = new Routing();
        System.out.println("TypeAdaptorRouting: testing je");
        int[][] simplePath = des.fromJson(je, int[][].class);
        ArrayList<int[]> e = new ArrayList<>();
        for (int[] temp : simplePath) {
            e.add(temp);
        }
        test.setPathingRoute(e);
        return test;
    }
    
}
