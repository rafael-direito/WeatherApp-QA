/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package weather_app.openweather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import weather_app.constants.Constants;
import static weather_app.restapi.WeatherApp.logger;
import static weather_app.restapi.WeatherApp.restTemplate;

/**
 *
 * @author rd
 */
public class OpenWeatherCalls
{
    public static Map<String, Double> getDataByHour(String city, DataType type)
    {
        Map<String, Double> openWeatherInfo = null;
        try
        {
            String date = "";
            
            openWeatherInfo = new TreeMap<>();
            GeneralOpenWeather gow = restTemplate.getForObject(Constants.getOpenWeatherForecast(city), GeneralOpenWeather.class);
            
            if(type == type.TEMPERATURE)
                for(ListOpenWeather low : gow.getList())
                {
                    date = low.getDtTxt();
                    openWeatherInfo.put(date, new Double(Math.round(Constants.kelvinToCelsius(low.getMain().getTemp()) * 100.00)/100.00));
                }
            else if(type == type.HUMIDITY)
                for(ListOpenWeather low : gow.getList())
                {
                    date = low.getDtTxt();
                    openWeatherInfo.put(date, new Double(Math.round(new Double(low.getMain().getHumidity()) * 100.00)/100.00));
                }                
        }
        catch (Exception exception) {
            logger.error("Unable to fetch data from OpenWeather");
            logger.error(exception.toString());
            return null;
        }
        return openWeatherInfo;
    }
    
    
    public static Map<String, Double> getDataByDay(String city, DataType type)
    {
        Map<String, Double> dataByHour = getDataByHour(city, type);
        
        if(dataByHour==null) return null;
            
        Map<String, List<Double>> tmp = new HashMap<>();
        String day;
        
        for(String key : dataByHour.keySet())
        {
            day = key.split(" ")[0];
            // if day not in map
            if(!tmp.containsKey(day))
                tmp.put(day,new ArrayList<Double>(Arrays.asList(dataByHour.get(key))));
            //if day is already in map
            else tmp.get(day).add(dataByHour.get(key));
        }
        
        Map<String, Double> openWeatherInfo = new HashMap<>();
        for(String key : tmp.keySet())
        {
            double sum = 0;
            for(Double d : tmp.get(key))
                sum += new Double(Math.round(d));
            openWeatherInfo.put(key, sum/tmp.get(key).size());
        }
        
        return openWeatherInfo;
    }
    
}
