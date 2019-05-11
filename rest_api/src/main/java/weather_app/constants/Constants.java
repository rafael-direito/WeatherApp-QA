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
public class Constants 
{
    private Constants(){}
    
    public static final String OPENWEATHER_APIKEY = "d75f1bf4b196f38e62994c662b6a87ef";
    
    public static final String getOpenWeatherForecast(String city) {
        return "http://api.openweathermap.org/data/2.5/forecast?q=" 
                + city 
                + ",PT&APPID=" + OPENWEATHER_APIKEY;
    }
    
    public static Double kelvinToCelsius(Double kelvin){ return kelvin - 273.15;}
    
    public static final String forecastKeyCache(String city) {return "Forecast"+city;}
    public static final String humidityDay(String city) {return "HumidityDay"+city;}
    public static final String humidityHour(String city) {return "HumidityHour"+city;}
    public static final String temperatureDay(String city) {return "TemperatureDay"+city;}
    public static final String temperatureHour(String city) {return "TemperatureHour"+city;}
    
    public static final String windDegreesToCardinal(Double degree)
    {
        if(degree >= 360-22.5 || degree < 22.5) return "N";
        else if (degree >= 22.5 && degree < 90-22.5) return "NE";
        else if (degree >= 90-22.5 && degree < 90+22.5) return "E";
        else if (degree >=  90+22.5 && degree < 180-22.5) return "SE";
        else if (degree >=  180-22.5 && degree < 180+22.5) return "S";
        else if (degree >=  180+22.5 && degree < 270-22.5) return "SW";
        else if (degree >=  270-22.5 && degree < 270+22.5) return "W";
        else  return "NW";
    }
    
    public static final String mostCommonElement(String[] array){
        Map<String, Integer> results = new HashMap<>();
        
        for(String e: array)
            if(!results.containsKey(e)) results.put(e, 1);
            else results.put(e, results.get(e) + 1);
        
        return Collections.max(results.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
    }
    
    
    public static final double averageDoubleFromArray(String[] array){
        double sum = 0;
        for(String s : array)
            sum += Double.parseDouble(s);
        
        return sum/array.length;
    }
}
