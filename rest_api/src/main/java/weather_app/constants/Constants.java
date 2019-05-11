/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.constants;

/**
 *
 * @author rd
 */
public class Constants 
{
    private Constants(){}
    
    public static final String openWeatherApiKey = "d75f1bf4b196f38e62994c662b6a87ef";
    
    public static final String getOpenWeatherForecast(String city) {
        return "http://api.openweathermap.org/data/2.5/forecast?q=" 
                + city 
                + ",PT&APPID=" + openWeatherApiKey;
    }
    
    public static Double kelvinToCelsius(Double kelvin){ return kelvin - 273.15;}
}
