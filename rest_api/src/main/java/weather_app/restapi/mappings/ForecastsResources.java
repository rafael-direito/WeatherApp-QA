package weather_app.restapi.mappings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weather_app.constants.Constants;
import weather_app.ipma.IpmaCalls;
import weather_app.openweather.OpenWeatherCalls;
import static weather_app.restapi.WeatherApp.mCache;

/**
 *
 * @author rd
 */
@RestController
@Api(value="Forecast Resources", tags = { "forecastResources" })
public class ForecastsResources 
{
    @ApiOperation("Returns a list of forecasts, regarding each day")
    @GetMapping("api/general_info/{city}/{num_days}")
    public  Map<String, Map<String, String>> generalInfo(
            @PathVariable("city") final String city,
            @PathVariable("num_days") final int num_days)
    {
        // Consult cache
        Map<String, Map<String, String>> merged = null;
        if(mCache.get(Constants.forecastKeyCache(city)) == null)
        {
            merged =  mergeIpmaOpenWeather(city);
            mCache.add(Constants.forecastKeyCache(city), merged, 180);
        }
        else
            merged =  (Map<String, Map<String, String>>) mCache.get(Constants.forecastKeyCache(city));
        
        // Select Number of days we want
        Map<String, Map<String, String>> results = new TreeMap<>();
        int count = 0;
        
        for(String k : merged.keySet())
            if (count<num_days){
                results.put(k, merged.get(k));
                count ++;
            }
            else break;
            
        return results;
    }
    
    
    @ApiOperation("Returns a forecast of a specific day")
    @GetMapping("api/specific_info/{city}/{day}")
    public Map<String, String> specificInfo(
            @PathVariable("city") final String city,
            @PathVariable("day") final String day)
    {
        // Consult the cache
        Map<String, Map<String, String>> merged = null;
        if(mCache.get(Constants.forecastKeyCache(city)) == null)
        {
            merged =  mergeIpmaOpenWeather(city);
            mCache.add(Constants.forecastKeyCache(city), merged, 180);
        }
        else
            merged =  (Map<String, Map<String, String>>) mCache.get(Constants.forecastKeyCache(city));

        return merged.get(day);
    }
    
    
    private Map<String, Map<String,String>> mergeIpmaOpenWeather (String city){
        
       Map<String, Map<String,String>> results = new TreeMap<>();
       Map<String, Map<String,String>> ipma = IpmaCalls.getForecast(city);
       Map<String, Map<String,String>> openWeather = OpenWeatherCalls.getForecast(city);
       
       // get forecast days
       Set<String> days = new TreeSet<>();
       days.addAll(ipma.keySet());
       days.addAll(openWeather.keySet());
       
       //fill the data
       for(String d : days)
       {
           Map<String,String> tmp = new TreeMap<>();
           //FillMap
           for (String k : new String[] 
               {"tMin", "tMax", "weather", "windDir", "windIntensity", "latitude",  
               "longitude", "precipitationProb", "humidity", "pressure"})
           fillMap(tmp, ipma, openWeather, d, k);
                      
           results.put(d, tmp);
       }
       return results;
    }
    
    
    private void fillMap(Map<String,String> mMap, Map<String, Map<String,String>> ipma, 
            Map<String, Map<String,String>> openWeather, String d, String key)
    {
        if(isEligible(ipma, d, key))
               mMap.put(key, ipma.get(d).get(key));
           else if (isEligible(openWeather, d,key))
               mMap.put(key, openWeather.get(d).get(key));
           else
               mMap.put(key, "none");
    }
    
    
    private boolean isEligible(Map<String, Map<String,String>> mMap, String d, String key)
    {
        if(mMap.containsKey(d) && mMap.get(d).containsKey(key) && mMap.get(d).get(key) != null)
            return true;
        return false;
    }
}
