/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.ipma;

import java.util.Map;
import java.util.TreeMap;
import static weather_app.restapi.WeatherApp.logger;
import static weather_app.restapi.WeatherApp.restTemplate;

/**
 *
 * @author rd
 */
public class IpmaCalls 
{
    public static  Map<String, Double> getTemperatures(String city)
    {
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
            ipmaInfo = new TreeMap<>();
            GeneralForecastIpma gf = restTemplate.getForObject("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+id+".json", GeneralForecastIpma.class);
            for(DailyForecastIpma df : gf.getData())
                ipmaInfo.put(df.getForecastDate(), (Double.parseDouble(df.getTMin()) + Double.parseDouble(df.getTMax()))/2);
        } 
        catch (Exception exception) {
            logger.error("Unable to fetch data from IPMA");
            logger.error(exception.toString());
            return null;
        }
        
        return ipmaInfo;
    }    
}
