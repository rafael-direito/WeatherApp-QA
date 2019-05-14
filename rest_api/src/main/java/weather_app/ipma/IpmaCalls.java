/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package weather_app.ipma;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.web.client.RestTemplate;
import weather_app.restapi.WeatherApp;
import static weather_app.restapi.WeatherApp.logger;
import static weather_app.restapi.WeatherApp.restTemplate;
import x.WeatherTypeIpma;
import x.WeatherTypeResponseIpma;
import x.WindTypeIpma;
import x.WindTypeResponseIpma;

/**
 *
 * @author rd
 */
public class IpmaCalls
{
    RestTemplate restTemplate; 

    

    public  GeneralForecastIpma getBaseInfo(String city)
    {
        if(restTemplate == null)
            restTemplate=WeatherApp.restTemplate;
            
        //get city ID
        PortugalDistrictsIpma pd = restTemplate.getForObject("http://api.ipma.pt/open-data/distrits-islands.json", PortugalDistrictsIpma.class);
        
        int id = -1;
        for(DistrictIpma d : pd.getData())
            if(d.getLocal().equals(city))
                id=d.getGlobalIdLocal();
        
        // get info for that district
        return restTemplate.getForObject("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+id+".json", GeneralForecastIpma.class);
    }
    
    public WeatherTypeIpma getWeatherType(int id)
    {
        if(restTemplate == null)
            restTemplate=WeatherApp.restTemplate;
        
        WeatherTypeResponseIpma wtr = restTemplate.getForObject("http://api.ipma.pt/open-data/weather-type-classe.json", WeatherTypeResponseIpma.class);
        for(WeatherTypeIpma wt : wtr.getData())
            if(wt.getIdWeatherType() == id)
                return wt;
        
        return null;
    }
    
    public  WindTypeIpma getWindType(int id)
    {
        if(restTemplate == null)
            restTemplate=WeatherApp.restTemplate;
        
        WindTypeResponseIpma wtr = restTemplate.getForObject("http://api.ipma.pt/open-data/wind-speed-daily-classe.json", WindTypeResponseIpma.class);
        for(WindTypeIpma wt : wtr.getData())
            if(wt.getClassWindSpeed().equals(id+""))
                return wt;
        
        return null;
    }
    
    
    public  Map<String, Double> getTemperatures(String city)
    {
        try {
            Map<String, Double> ipmaInfo = new TreeMap<>();
            GeneralForecastIpma gf = getBaseInfo(city);
            for(DailyForecastIpma df : gf.getData())
            {
                ipmaInfo.put(df.getForecastDate(), (Double.parseDouble(df.getTMin()) + Double.parseDouble(df.getTMax()))/2);
            }
            
            return ipmaInfo;
        }
        catch (Exception exception) {
            logger.error("Unable to fetch data from IPMA");
            logger.error(exception.toString());
            return null;
        }
    }
    
    
    public Map<String, Map<String, String>> getForecast(String city)
    {
        try {
            Map<String, Map<String, String>> ipmaInfo = new TreeMap<>();
            GeneralForecastIpma gf = getBaseInfo(city);
            
            Map<String, String> tmp;
            for(DailyForecastIpma df : gf.getData())
            {
                tmp = new HashMap<>();
                tmp.put("tMin", df.getTMin());
                tmp.put("tMax", df.getTMax());
                tmp.put("precipitationProb", df.getPrecipitaProb());
                tmp.put("latitude", df.getLatitude());
                tmp.put("longitude", df.getLongitude());
                tmp.put("windDir", df.getPredWindDir());
                
                WeatherTypeIpma weatherTypeIpma = getWeatherType(df.getIdWeatherType());
                if(weatherTypeIpma!=null) tmp.put("weather",weatherTypeIpma.getDescIdWeatherTypeEN());
                else tmp.put("weather","none"); 
                                    
                WindTypeIpma windTypeIpma =  getWindType(df.getClassWindSpeed()); 
                if(windTypeIpma!=null) tmp.put("windIntensity",windTypeIpma.getDescClassWindSpeedDailyEN());
                else tmp.put("windIntensity","none"); 
                                 
                ipmaInfo.put(df.getForecastDate(), tmp);                
            }
            return ipmaInfo;
        }
        catch (Exception exception) {
            logger.error("Unable to fetch data from IPMA");
            logger.error(exception.toString());
            return null;
        }
    }
    
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    
}
