/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author rd
 */
public class Calculations 
{
    public final String mostCommonElement(String[] array){
        Map<String, Integer> results = new HashMap<>();
        
        for(String e: array)
            if(!results.containsKey(e)) results.put(e, 1);
            else results.put(e, results.get(e) + 1);
        
        return Collections.max(results.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
    }
    
    public double averageDoubleFromArray(String[] array){
        double sum = 0;
        for(String s : array)
            sum += Double.parseDouble(s);
        
        return sum/array.length;
    }
    
}
