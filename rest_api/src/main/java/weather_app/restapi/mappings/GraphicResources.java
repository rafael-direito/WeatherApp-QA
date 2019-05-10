package weather_app.restapi.mappings;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author rd
 */
@RestController
@Api(value="Graphic Resources", tags = { "graphicResources" })
public class GraphicResources 
{
    @ApiOperation("Returns a b64 image for a specific weather type")
    @GetMapping("weather_icon/{weather_id}")
    public String getWeatherIcpon(@PathVariable("weather_id") final long weather_id)
    {return "";}
    
  
    
}
