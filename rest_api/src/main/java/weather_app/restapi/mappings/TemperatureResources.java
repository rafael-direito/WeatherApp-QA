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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Object> getTemperatureByDay(@PathVariable("city") final String city)
    {return new ArrayList<>();}
    
    @ApiOperation("Returns a list of temperatures spaced by 3 hours")
    @GetMapping("temperatures/hour/{city}")
    public List<Object> getTemperatureByHour(@PathVariable("city") final String city)
    {return new ArrayList<>();}
    
}
