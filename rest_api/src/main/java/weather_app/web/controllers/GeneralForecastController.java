/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.web.controllers;

import weather_app.web.forms.ForecastNumberDays;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import weather_app.constants.Converters;
import weather_app.restapi.mappings.ForecastsResources;

/**
 *
 * @author rd
 */
@Controller
public class GeneralForecastController 
{
    private ForecastsResources fr = new ForecastsResources();

    @GetMapping("/generalForecast")
    public String generalForecast(
            @RequestParam(name="city", required=false, defaultValue="Lisboa") String city,
            @RequestParam(name="numDays", required=false, defaultValue="5") String numDays,
            Model model) 
    {
        System.out.println("akjshfjafash");
        
        // get Forecasts
        TreeMap<String, Map<String, String>> forecasts = (TreeMap<String, Map<String, String>>) fr.generalInfo(city, Integer.parseInt(numDays));
        // add Atributes
        model.addAttribute("city", city);
        model.addAttribute("numDays", Integer.parseInt(numDays));
        model.addAttribute("forecasts", forecasts);
        model.addAttribute("forecastNumberDays", new ForecastNumberDays());
        
        return "generalForecast";
    }
        
    @PostMapping("/generalForecast")
    public RedirectView generalForecastSubmit(@ModelAttribute ForecastNumberDays forecastNumberDays) 
    {
        String city = forecastNumberDays.getCity();
        int numDays = forecastNumberDays.getNumDays();
                
        RedirectView rv = new RedirectView();
        rv.setContextRelative(true);
        String mUrls = "/generalForecast";
        if(!(city.equals("") && numDays==0))
            mUrls+="?";
        if(!city.equals(""))
            mUrls+="city="+city;
        if(numDays != 0)
            mUrls+="&numDays="+numDays;
                
        rv.setUrl(mUrls);
        return rv;
    }
    
    
    @GetMapping("/specificForecast")
    public String specificForecast(
            @RequestParam(name="city", required=false, defaultValue="Lisboa") String city,
            @RequestParam(name="day", required=false, defaultValue="") String day,
            Model model) 
    {
        // get Forecasts
        Map<String, String> forecast = fr.specificInfo(city, day);
        // add Atributes
        model.addAttribute("city", city);
        model.addAttribute("day", day);
        model.addAttribute("dayStr", Converters.dateToString(day));
        model.addAttribute("forecast", forecast);
        
        return "specificForecast";
    }

}