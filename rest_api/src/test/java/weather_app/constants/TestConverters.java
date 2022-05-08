package weather_app.constants;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import weather_app.constants.Converters;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rd
 */
public class TestConverters 
{
    Converters converters = new Converters();
    
    public TestConverters() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    

    @Test
    public void checkDateToString()
    {
        assertEquals("January, 1st", converters.dateToString("2019-01-01"));
        assertEquals("February, 2nd", converters.dateToString("2019-02-02") );
        assertEquals("March, 3rd", converters.dateToString("2019-03-03"));
        assertEquals("April, 21st",converters.dateToString("2019-04-21"));
        assertEquals("May, 22nd", converters.dateToString("2019-05-22"));
        assertEquals("June, 23rd", converters.dateToString("2019-06-23"));
        assertEquals("July, 31st",converters.dateToString("2019-07-31"));
        assertEquals("August, 12th", converters.dateToString("2019-08-12"));
        assertEquals("September, 12th", converters.dateToString("2019-09-12"));
        assertEquals("October, 12th",converters.dateToString("2019-10-12"));
        assertEquals("November, 12th", converters.dateToString("2019-11-12"));
        assertEquals("December, 12th", converters.dateToString("2019-12-12"));
        //default
        assertEquals("-, 12th", converters.dateToString("2019-00-12"));
    }
    
    
    @Test
    public void checkDateToDayOfWeek()
    {
        assertEquals("Sunday", converters.dateToDayOfWeek("2019-05-12"));
        assertEquals("error", converters.dateToDayOfWeek("2019/05/12"));
    }
    
    @Test
    public void windDegreesToCardinal()
    {
        assertEquals("N", converters.windDegreesToCardinal(0.0));
        assertEquals("N", converters.windDegreesToCardinal(360.0));
        assertEquals("NE", converters.windDegreesToCardinal(45.0));
        assertEquals("E", converters.windDegreesToCardinal(90.0));
        assertEquals("SE", converters.windDegreesToCardinal(45.0+90.0));
        assertEquals("S", converters.windDegreesToCardinal(180.0));
        assertEquals("SW", converters.windDegreesToCardinal(180.0+45));
        assertEquals("W", converters.windDegreesToCardinal(270.0));
        assertEquals("NW", converters.windDegreesToCardinal(270.0+45));
        assertEquals("None", converters.windDegreesToCardinal(10000.5));
    }
    
    
    @Test
    public void weatherToIpma()
    {
        assertEquals("Clear sky", converters.weatherToIpma("clear sky"));
        assertEquals("Partly cloudy", converters.weatherToIpma("few clouds"));
        assertEquals("Cloudy", converters.weatherToIpma("scattered clouds"));
        assertEquals("Cloudy (High cloud)", converters.weatherToIpma("broken clouds"));
        assertEquals("Showers", converters.weatherToIpma("shower rain"));
        assertEquals("Rain", converters.weatherToIpma("rain"));
        assertEquals("Thunderstorms", converters.weatherToIpma("thunderstorm"));
        assertEquals("Snow", converters.weatherToIpma("snow"));
        assertEquals("Mist", converters.weatherToIpma("mist"));
        assertEquals("No information", converters.weatherToIpma("xxx"));
    }
    

}
