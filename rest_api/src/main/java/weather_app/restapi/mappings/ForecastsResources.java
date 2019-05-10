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
@Api(value="Forecast Resources", tags = { "forecastResources" })
public class ForecastsResources 
{
    @ApiOperation("Returns a list of forecasts, regarding each day")
    @GetMapping("general_info/{city}/{num_days}")
    public List<Object> generalInfo(
            @PathVariable("city") final String city,
            @PathVariable("num_days") final int num_days)
    {return new ArrayList<>();}
    
    @ApiOperation("Returns a forecast of a specific day")
    @GetMapping("specific_info/{city}/{day}")
    public List<Object> specificInfo(
            @PathVariable("city") final String city,
            @PathVariable("day") final int day)
    {return new ArrayList<>();}
    
}
