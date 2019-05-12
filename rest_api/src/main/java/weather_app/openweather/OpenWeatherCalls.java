/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package weather_app.openweather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
                    openWeatherInfo.put(date, Math.round(new Double(low.getMain().getHumidity()) * 100.00)/100.00);
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
    
    
    public static Map<String, Map<String,String>> getForecast(String city)
    {
        
        try{
            Set<HourForecast> hourForecasts =  new HashSet<>();
            Map<String,Map<String,String>> openWeatherInfo = new TreeMap<>();
            List<String> days = new ArrayList<>();
            
            GeneralOpenWeather gow = restTemplate.getForObject(Constants.getOpenWeatherForecast(city), GeneralOpenWeather.class);
            for(ListOpenWeather low : gow.getList())
            {
                HourForecast hf = new HourForecast();
                hf.settMin(low.getMain().getTempMin());
                hf.settMax(low.getMain().getTempMax());
                hf.setWeatherType(Constants.weatherToIpma(low.getWeather().get(0).getDescription()));
                hf.setTime(low.getDtTxt());
                hf.setHumidity(new Double(low.getMain().getHumidity()));
                hf.setWindDir(Constants.windDegreesToCardinal(low.getWind().getDeg()));
                hf.setPressure(new Double(low.getMain().getPressure()));
                hf.setLongitude(gow.getCity().getCoord().getLon());
                hf.setLatitude(gow.getCity().getCoord().getLat());
                
                hourForecasts.add(hf);
            }
            
            String day = "";
            for(HourForecast hf : hourForecasts)
            {
                day = hf.getTime().split(" ")[0];
                if(!days.contains(day)) days.add(day);
                
                // if day not in openWeatherInfo
                if(!openWeatherInfo.keySet().contains(day))
                {
                    openWeatherInfo.put(day, new HashMap<>());
                    openWeatherInfo.get(day).put("tMin", Double.MAX_VALUE + "");
                    openWeatherInfo.get(day).put("tMax", Double.MIN_VALUE + "");
                    openWeatherInfo.get(day).put("weather", "");
                    openWeatherInfo.get(day).put("humidity", "");
                    openWeatherInfo.get(day).put("windDir", "");
                    openWeatherInfo.get(day).put("pressure", "");
                }
                
                // add different data to map
                openWeatherInfo.get(day).put("weather",  openWeatherInfo.get(day).get("weather") + hf.getWeatherType() + " ");
                openWeatherInfo.get(day).put("humidity",  openWeatherInfo.get(day).get("humidity") + hf.getHumidity()+ " ");
                openWeatherInfo.get(day).put("windDir",  openWeatherInfo.get(day).get("windDir") + hf.getWindDir()+ " ");
                openWeatherInfo.get(day).put("pressure",  openWeatherInfo.get(day).get("pressure") + hf.getPressure()+ " ");
                
                if( Constants.kelvinToCelsius(hf.gettMin()) < Constants.kelvinToCelsius(Double.parseDouble(openWeatherInfo.get(day).get("tMin"))))
                    openWeatherInfo.get(day).put("tMin", Math.round(Constants.kelvinToCelsius(hf.gettMin()) *100.00)/100.00 + "");
                
                if( Constants.kelvinToCelsius(hf.gettMax())> Constants.kelvinToCelsius(Double.parseDouble(openWeatherInfo.get(day).get("tMax"))))
                    openWeatherInfo.get(day).put("tMax", Math.round(Constants.kelvinToCelsius(hf.gettMax()) *100.00)/100.00 + "");
                
                if(!openWeatherInfo.get(day).containsKey("longitude"))
                    openWeatherInfo.get(day).put("longitude", hf.getLongitude() + "");
                
                if(!openWeatherInfo.get(day).containsKey("latitude"))
                    openWeatherInfo.get(day).put("latitude", hf.getLatitude()+ "");
            }
            
            for(String d : days)
            {
                openWeatherInfo.get(d).put("weather", Constants.mostCommonElement(openWeatherInfo.get(d).get("weather").split(" ")));
                openWeatherInfo.get(d).put("windDir", Constants.mostCommonElement(openWeatherInfo.get(d).get("windDir").split(" ")));
                openWeatherInfo.get(d).put("humidity", Constants.averageDoubleFromArray(openWeatherInfo.get(d).get("humidity").split(" ")) + "");
                openWeatherInfo.get(d).put("pressure", Constants.averageDoubleFromArray(openWeatherInfo.get(d).get("pressure").split(" ")) + "");
            }
            
            return openWeatherInfo;
            
        }
        catch (Exception exception) {
            logger.error("Unable to fetch data from OpenWeather");
            logger.error(exception.toString());
            return null;
        }
    }   
}
