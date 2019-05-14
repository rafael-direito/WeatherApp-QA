/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package weather_app.constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author rd
 */
public class Converters
{

    public String windDegreesToCardinal(Double degree)
    {
        if((degree >= 360-22.5  && degree <=360)|| (degree>=0 && degree < 22.5)) return "N";
        else if (degree >= 22.5 && degree < 90-22.5) return "NE";
        else if (degree >= 90-22.5 && degree < 90+22.5) return "E";
        else if (degree >=  90+22.5 && degree < 180-22.5) return "SE";
        else if (degree >=  180-22.5 && degree < 180+22.5) return "S";
        else if (degree >=  180+22.5 && degree < 270-22.5) return "SW";
        else if (degree >=  270-22.5 && degree < 270+22.5) return "W";
        else if (degree >=  270+22.5 && degree < 360-22.5) return "NW";
        else  return "None";
    }
    
    public String weatherToIpma(String openWeatherDescription)
    {
        switch(openWeatherDescription)
        {
            case "clear sky":
                return "Clear sky";
            case "few clouds":
                return 	"Partly cloudy";
            case "scattered clouds":
                return 	"Cloudy";
            case "broken clouds":
                return 	"Cloudy (High cloud)";
            case "shower rain":
                return "Showers";
            case "rain":
                return "Rain";
            case "thunderstorm":
                return "Thunderstorms";
            case "snow":
                return "Snow";
            case "mist":
                return "Mist";
            default:
                return "No information";
        }
    }
    
    public String dateToDayOfWeek(String date)
    {
        try {
            SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd");
            Date dt1=format1.parse(date);
            DateFormat format2=new SimpleDateFormat("EEEE");
            return format2.format(dt1);
        } catch (ParseException ex) {return "error";}
    }
    
    
    public String dateToString(String date)
    {
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
        
        String monthStr = "";
        String dayStr = "";
        
        switch(month)
        {
            case 1 :
                monthStr="January";
                break;
            case 2 :
                monthStr="February";
                break;
            case 3 :
                monthStr="March";
                break;
            case 4 :
                monthStr="April";
                break;
            case 5 :
                monthStr="May";
                break;
            case 6 :
                monthStr="June";
                break;
            case 7 :
                monthStr="July";
                break;
            case 8 :
                monthStr="August";
                break;
            case 9 :
                monthStr="September";
                break;
            case 10 :
                monthStr="October";
                break;
            case 11 :
                monthStr="November";
                break;
            case 12 :
                monthStr="December";
                break;
            default:
                monthStr="-";
        }
        
        switch(day)
        {
            case 1 :
                dayStr="1st";
                break;
            case 2 :
                dayStr="2nd";
                break;
            case 3 :
                dayStr="3rd";
                break;
            case 21 :
                dayStr="21st";
                break;
            case 22 :
                dayStr="22nd";
                break;
            case 23 :
                dayStr="23rd";
                break;
            case 31 :
                dayStr="31st";
                break;
            default:
                dayStr=day+"th";
        }
        return monthStr + ", " + dayStr;
    }
    
}
