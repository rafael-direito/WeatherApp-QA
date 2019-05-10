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
import weather_app.data.ipma.DailyForecastIpma;
import weather_app.data.ipma.GeneralForecastIpma;
import weather_app.data.openweather.GeneralOpenWeather;
import weather_app.data.openweather.ListOpenWeather;
import x.DistrictIpma;
import x.PortugalDistrictsIpma;

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
        /* 
        IPMA Data 
        */
        Map<String, Double> ipmaInfo = null;
        try 
        {
            //get city ID
            PortugalDistrictsIpma pd = restTemplate.getForObject("http://api.ipma.pt/open-data/distrits-islands.json", PortugalDistrictsIpma.class);
            
            int id = -1;
            
            for(DistrictIpma d : pd.getData())
                if(d.getLocal().equals(city))
                    id=d.getGlobalIdLocal();
            
            // get info for that district
            ipmaInfo = new HashMap<>();
            GeneralForecastIpma gf = restTemplate.getForObject("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+id+".json", GeneralForecastIpma.class);
            for(DailyForecastIpma df : gf.getData())
                ipmaInfo.put(df.getForecastDate(), (Double.parseDouble(df.getTMin()) + Double.parseDouble(df.getTMax()))/2);
        } 
        catch (Exception exception) {
            System.out.println("Unable to fetch data from IPMA");
            System.out.println(exception);
        }
        
        /* 
        Open Weather Data 
        */
        Map<String, Double> openWeatherInfo = null;
        try 
        {
            String day = "";
            
            Map<String, List<Double>> tmpOpenWeatherInfo = new HashMap<>();
            GeneralOpenWeather gow = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/forecast?q=" + city +",PT&APPID=" + Constants.openWeatherApiKey, GeneralOpenWeather.class);
            System.out.println("http://api.openweathermap.org/data/2.5/forecast?q=" + city +",PT&APPID=" + Constants.openWeatherApiKey);    
            for(ListOpenWeather low : gow.getList())
            {
                // if day is not already in map
                day = low.getDtTxt().split(" ")[0];
                if(!tmpOpenWeatherInfo.containsKey(day))
                    
                    tmpOpenWeatherInfo.put(
                            day,
                            new ArrayList<Double>(Arrays.asList(Constants.kelvinToCelsius(low.getMain().getTemp())))
                    );
                //if day is already in map
                else tmpOpenWeatherInfo.get(day).add(Constants.kelvinToCelsius(low.getMain().getTemp()));
            }
            // do averages to get daily temperatures
            openWeatherInfo = new HashMap<>();
            for(String key : tmpOpenWeatherInfo.keySet())
            {
                double sum = 0;
                for(Double d : tmpOpenWeatherInfo.get(key))
                    sum += new Double(Math.round(d));
                openWeatherInfo.put(key, sum/tmpOpenWeatherInfo.get(key).size());
            }
        } 
        catch (Exception exception) {
            System.out.println("Unable to fetch data from OpenWeather");
            System.out.println(exception);
        }
        
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
        Map<String, Double> openWeatherInfo = null;
        try 
        {
            String date = "";
            
            openWeatherInfo = new TreeMap<>();
            GeneralOpenWeather gow = restTemplate.getForObject("http://api.openweathermap.org/data/2.5/forecast?q=" + city +",PT&APPID=" + Constants.openWeatherApiKey, GeneralOpenWeather.class);
            System.out.println("http://api.openweathermap.org/data/2.5/forecast?q=" + city +",PT&APPID=" + Constants.openWeatherApiKey);    
            for(ListOpenWeather low : gow.getList())
            {
                date = low.getDtTxt();
                openWeatherInfo.put(date, new Double(Math.round(Constants.kelvinToCelsius(low.getMain().getTemp()) * 100.00)/100.00));
            }
        } 
        catch (Exception exception) {
            System.out.println("Unable to fetch data from OpenWeather");
            System.out.println(exception);
        }
        return openWeatherInfo;
    }
    
}
