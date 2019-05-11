/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import static application.Application.logger;
import static application.Application.restTemplate;
import constants.Constants;
import forms.ForecastNumberDays;
import java.util.Map;
import java.util.TreeMap;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

/**
 *
 * @author rd
 */
@Controller
public class GeneralForecastController {

    @GetMapping("/generalForecast")
    public String generalForecast(
            @RequestParam(name="city", required=false, defaultValue="Lisboa") String city,
            @RequestParam(name="numDays", required=false, defaultValue="5") String numDays,
            Model model) 
    {
        System.out.println(Constants.dateToDayOfWeek("2019-05-13"));
        // get Forecasts
        TreeMap<String, Map<String, String>> forecasts = restTemplate.getForObject(Constants.BASE_API + "general_info/" + city +"/" + numDays, TreeMap.class);
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
        
        System.out.println(numDays);
        
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
         Map<String, String> forecast = restTemplate.getForObject(Constants.BASE_API + "specific_info/" + city +"/" + day, Map.class);
        // add Atributes
        model.addAttribute("city", city);
        model.addAttribute("day", day);
        model.addAttribute("dayStr", Constants.dateToString(day));
        model.addAttribute("forecast", forecast);
        
        return "specificForecast";
    }

}