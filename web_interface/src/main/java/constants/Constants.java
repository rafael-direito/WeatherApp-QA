/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package constants;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rd
 */
public class Constants
{
    public Constants(){};
    
    public static final String BASE_API = "http://localhost:8081/";
    
    
    public static final String buildGeneralInfoPath(String city, String numDays)
    {return BASE_API + "general_info/" + city +"/" + numDays;}
    
    public static final String buildSpecificInfoPath(String city, String day)
    {return BASE_API + "specific_info/" + city +"/" + day;}
    
    public static final String dateToDayOfWeek(String date)
    {
        try {
            SimpleDateFormat format1= new SimpleDateFormat("yyyy-MM-dd");
            Date dt1=format1.parse(date);
            DateFormat format2=new SimpleDateFormat("EEEE");
            return format2.format(dt1);
        } catch (ParseException ex) {return "error";}
    }
    
    
    public static final String dateToString(String date)
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
        
        switch(month)
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
