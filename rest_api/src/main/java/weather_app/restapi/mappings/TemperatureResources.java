package weather_app.restapi.mappings;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weather_app.cache.MCache;
import weather_app.constants.Constants;
import weather_app.ipma.IpmaCalls;
import weather_app.openweather.DataType;
import weather_app.openweather.OpenWeatherCalls;
import weather_app.restapi.WeatherApp;
import static weather_app.restapi.WeatherApp.logger;

/**
 *
 * @author rd
 */
@RestController
@Api(value="Temperature Resources",tags = { "temperatureResources" })
public class TemperatureResources
{
    private Constants constants = new Constants();
    private MCache mCache;
    private OpenWeatherCalls openWeatherCalls = new OpenWeatherCalls();
    private IpmaCalls ipmaCalls = new IpmaCalls();
            
    @ApiOperation("Returns a list of temperatures, regarding each day")
    @GetMapping("api/temperatures/day/{city}")
    public Map<String, Double> getTemperatureByDay(@PathVariable("city") final String city)
    {
        if(mCache==null) setmCache(WeatherApp.mCache);
        // Consult cache
        Map<String,Double> data = null;
        if(mCache.get(constants.temperatureDay(city)) == null)
        {
            data = generateTemperatureByDay(city);
            logger.info("Writing to cache");
            mCache.add(constants.temperatureDay(city), data, 180);
        }
        else
        {
            data = (Map<String,Double>) mCache.get(constants.temperatureDay(city));
        }
        
        return data;
    }
    
    
    @ApiOperation("Returns a list of temperatures spaced by 3 hours")
    @GetMapping("api/temperatures/hour/{city}")
    public Map<String, Double> getTemperatureByHour(@PathVariable("city") final String city)
    {
      Map<String,Double> data = null;
      if(mCache==null) setmCache(WeatherApp.mCache);
      // Consult cache
        if(mCache.get(constants.temperatureHour(city)) == null)
        {
            data = openWeatherCalls.getDataByHour(city, DataType.TEMPERATURE);
            mCache.add(constants.temperatureHour(city), data, 180);
        }
        else
            data = (Map<String,Double>) mCache.get(constants.temperatureHour(city));
        
        return data;
    }
    
    
    public  Map<String, Double> generateTemperatureByDay(String city)
    {
       // get data
        Map<String, Double> ipmaInfo = ipmaCalls.getTemperatures(city);
        Map<String, Double> openWeatherInfo = openWeatherCalls.getDataByDay(city, DataType.TEMPERATURE);
        if (ipmaInfo == null && openWeatherInfo == null)
            return new HashMap<>();
        else if (ipmaInfo == null)
            return openWeatherInfo;
        else if (openWeatherInfo == null)
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
    
    
    public void setmCache(MCache mCache) {this.mCache=mCache;}
    public void setOpenWeatherCalls(OpenWeatherCalls openWeatherCalls) {this.openWeatherCalls=openWeatherCalls;}
    public void setIpmaCalls(IpmaCalls ipmaCalls) {this.ipmaCalls=ipmaCalls;}

}
