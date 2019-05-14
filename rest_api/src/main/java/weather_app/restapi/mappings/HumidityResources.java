package weather_app.restapi.mappings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weather_app.cache.MCache;
import weather_app.constants.Constants;
import weather_app.openweather.DataType;
import weather_app.openweather.OpenWeatherCalls;
import weather_app.restapi.WeatherApp;

/**
 *
 * @author rd
 */
@RestController
@Api(value="Humidity Resources",  tags = { "humidityResources" })
public class HumidityResources 
{
    private Constants constants = new Constants();
    private MCache mCache;
    private OpenWeatherCalls openWeatherCalls = new OpenWeatherCalls();
    
    @ApiOperation("Returns a list of humidities, regarding each day")
    @GetMapping("api/humidities/day/{city}")
    public Map<String,Double> getHumidityByDay(@PathVariable("city") final String city)
    {
        if(mCache==null) setmCache(WeatherApp.mCache);
        // Consult cache
        Map<String,Double> data = null;
        if(mCache.get(constants.humidityDay(city)) == null)
        {
            data = openWeatherCalls.getDataByDay(city, DataType.HUMIDITY);
            mCache.add(constants.humidityDay(city), data, 180);
        }
        else
            data = (Map<String,Double>) mCache.get(constants.humidityDay(city));
        
        return data;
    }
    
    @ApiOperation("Returns a list of humidities spaced by 3 hours")
    @GetMapping("api/humidities/hour/{city}")
    public Map<String,Double> getHumidityByHour (@PathVariable("city") final String city)
    {
        if(mCache==null) setmCache(WeatherApp.mCache);
        // Consult cache
        Map<String,Double> data = null;
        if(mCache.get(constants.humidityHour(city)) == null)
        {
            data = openWeatherCalls.getDataByHour(city, DataType.HUMIDITY);
            mCache.add(constants.humidityHour(city), data, 180);
        }
        else
            data = (Map<String,Double>) mCache.get(constants.humidityHour(city));
        
        return data;
    }
    
    public void setmCache(MCache mCache) {
        this.mCache = mCache;
    }

    public void setOpenWeatherCalls(OpenWeatherCalls openWeatherCalls) {
        this.openWeatherCalls = openWeatherCalls;
    }
    
}
