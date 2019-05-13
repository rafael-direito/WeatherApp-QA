package weather_app.restapi.mappings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import weather_app.constants.Constants;
import weather_app.openweather.DataType;
import weather_app.openweather.OpenWeatherCalls;
import static weather_app.restapi.WeatherApp.mCache;

/**
 *
 * @author rd
 */
@RestController
@Api(value="Humidity Resources",  tags = { "humidityResources" })
public class HumidityResources 
{
    @ApiOperation("Returns a list of humidities, regarding each day")
    @GetMapping("api/humidities/day/{city}")
    public Map<String,Double> getHumidityByDay(@PathVariable("city") final String city)
    {
        // Consult cache
        Map<String,Double> data = null;
        if(mCache.get(Constants.humidityDay(city)) == null)
        {
            data = OpenWeatherCalls.getDataByDay(city, DataType.HUMIDITY);
            mCache.add(Constants.humidityDay(city), data, 180);
        }
        else
            data = (Map<String,Double>) mCache.get(Constants.humidityDay(city));
        
        return data;
    }
    
    @ApiOperation("Returns a list of humidities spaced by 3 hours")
    @GetMapping("api/humidities/hour/{city}")
    public Map<String,Double> getHumidityByHour (@PathVariable("city") final String city)
    {
        // Consult cache
        Map<String,Double> data = null;
        if(mCache.get(Constants.humidityHour(city)) == null)
        {
            data = OpenWeatherCalls.getDataByHour(city, DataType.HUMIDITY);
            mCache.add(Constants.humidityHour(city), data, 180);
        }
        else
            data = (Map<String,Double>) mCache.get(Constants.humidityHour(city));
        
        return data;
    }
    
}
