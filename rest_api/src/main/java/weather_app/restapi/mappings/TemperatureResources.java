package weather_app.restapi.mappings;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weather_app.constants.Constants;
import static weather_app.restapi.WeatherApp.restTemplate;
import weather_app.openweather.GeneralOpenWeather;
import weather_app.openweather.ListOpenWeather;
import weather_app.ipma.IpmaCalls;
import weather_app.openweather.OpenWeatherCalls;

/**
 *
 * @author rd
 */
@RestController
@Api(value="Temperature Resources",tags = { "temperatureResources" })
public class TemperatureResources
{
    @ApiOperation("Returns a list of temperatures, regarding each day")
    @GetMapping("temperatures/day/{city}")
    public Map<String, Double> getTemperatureByDay(@PathVariable("city") final String city)
    {
        // get data
        Map<String, Double> ipmaInfo = IpmaCalls.getTemperatures(city);
        Map<String, Double> openWeatherInfo = OpenWeatherCalls.getTemperaturesByDay(city);
        
        
        if (ipmaInfo == null && openWeatherInfo == null)
            return new HashMap<>();
        else if (ipmaInfo == null && openWeatherInfo != null)
            return openWeatherInfo;
        else if (ipmaInfo != null && openWeatherInfo == null)
            return ipmaInfo;
        else
        {
            Map<String,Double> avgTemp = new TreeMap<>();
            Set<String> days = new HashSet<>();
            days.addAll(ipmaInfo.keySet());
            days.addAll(openWeatherInfo.keySet());
            
            for(String d : days)
                if(ipmaInfo.containsKey(d) && openWeatherInfo.containsKey(d))
                    avgTemp.put(d, (ipmaInfo.get(d) + openWeatherInfo.get(d))/2);
                else if (ipmaInfo.containsKey(d))
                     avgTemp.put(d, ipmaInfo.get(d));
                else if (openWeatherInfo.containsKey(d))
                     avgTemp.put(d, openWeatherInfo.get(d));
                
            return avgTemp;        
        }  
    }
    
    
    @ApiOperation("Returns a list of temperatures spaced by 3 hours")
    @GetMapping("temperatures/hour/{city}")
    public Map<String, Double> getTemperatureByHour(@PathVariable("city") final String city)
    {
      return OpenWeatherCalls.getTemperaturesByHour(city);
    }
    
}
