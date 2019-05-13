
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.gen5.api.Assertions.assertThrows;
import org.junit.gen5.api.DisplayName;
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
    @DisplayName("From Date to String")
    public void checkDateToString()
    {
        assertEquals("January, 1st", Converters.dateToString("2019-01-01"));
        assertEquals("February, 2nd", Converters.dateToString("2019-02-02") );
        assertEquals("March, 3rd", Converters.dateToString("2019-03-03"));
        assertEquals("April, 21st",Converters.dateToString("2019-04-21"));
        assertEquals("May, 22nd", Converters.dateToString("2019-05-22"));
        assertEquals("June, 23rd", Converters.dateToString("2019-06-23"));
        assertEquals("July, 31st",Converters.dateToString("2019-07-31"));
        assertEquals("August, 12th", Converters.dateToString("2019-08-12"));
        assertEquals("September, 12th", Converters.dateToString("2019-09-12"));
        assertEquals("October, 12th",Converters.dateToString("2019-10-12"));
        assertEquals("November, 12th", Converters.dateToString("2019-11-12"));
        assertEquals("December, 12th", Converters.dateToString("2019-12-12"));
        //default
        assertEquals("-, 12th", Converters.dateToString("2019-00-12"));
    }
    
    
    @Test
    @DisplayName("From Date to Day of The Week")
    public void checkDateToDayOfWeek()
    {
        assertEquals("Sunday", Converters.dateToDayOfWeek("2019-05-12"));
        assertEquals("error", Converters.dateToDayOfWeek("2019/05/12"));
    }
    
    @Test
    @DisplayName("windDegreesToCardinal")
    public void windDegreesToCardinal()
    {
        assertEquals("N", Converters.windDegreesToCardinal(0.0));
        assertEquals("N", Converters.windDegreesToCardinal(360.0));
        assertEquals("NE", Converters.windDegreesToCardinal(45.0));
        assertEquals("E", Converters.windDegreesToCardinal(90.0));
        assertEquals("SE", Converters.windDegreesToCardinal(45.0+90.0));
        assertEquals("S", Converters.windDegreesToCardinal(180.0));
        assertEquals("SW", Converters.windDegreesToCardinal(180.0+45));
        assertEquals("W", Converters.windDegreesToCardinal(270.0));
        assertEquals("NW", Converters.windDegreesToCardinal(270.0+45));
        assertEquals("None", Converters.windDegreesToCardinal(10000.5));
    }
    
    
    @Test
    @DisplayName("weatherToIpma")
    public void weatherToIpma()
    {
        assertEquals("Clear sky", Converters.weatherToIpma("clear sky"));
        assertEquals("Partly cloudy", Converters.weatherToIpma("few clouds"));
        assertEquals("Cloudy", Converters.weatherToIpma("scattered clouds"));
        assertEquals("Cloudy (High cloud)", Converters.weatherToIpma("broken clouds"));
        assertEquals("Showers", Converters.weatherToIpma("shower rain"));
        assertEquals("Rain", Converters.weatherToIpma("rain"));
        assertEquals("Thunderstorms", Converters.weatherToIpma("thunderstorm"));
        assertEquals("Snow", Converters.weatherToIpma("snow"));
        assertEquals("Mist", Converters.weatherToIpma("mist"));
        assertEquals("No information", Converters.weatherToIpma("xxx"));
    }
}
