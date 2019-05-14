/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.constants;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rd
 */
public class ConstantsTest {
    
    Constants constants = new Constants();
    
    public ConstantsTest() {
    }
    
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getOpenWeatherForecast method, of class constants.
     */
    @Test
    public void testGetOpenWeatherForecast() {
        System.out.println("getOpenWeatherForecast");
        String city = "Aveiro";
        String expResult = "http://api.openweathermap.org/data/2.5/forecast?q="+ city + ",PT&APPID=" + constants.OPENWEATHERAPIKEY;
        String result = constants.getOpenWeatherForecast(city);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of kelvinToCelsius method, of class constants.
     */
    @Test
    public void testKelvinToCelsius() {
        System.out.println("kelvinToCelsius");
        Double kelvin = 1.0;
        Double expResult = -272.15;
        Double result = constants.kelvinToCelsius(kelvin);
        assertEquals(expResult, result);
    }

    /**
     * Test of forecastKeyCache method, of class constants.
     */
    @Test
    public void testForecastKeyCache() {
        System.out.println("forecastKeyCache");
        String city = "Aveiro";
        String expResult = "Forecast"+city;
        String result = constants.forecastKeyCache(city);
        assertEquals(expResult, result);
    }

    /**
     * Test of humidityDay method, of class constants.
     */
    @Test
    public void testHumidityDay() {
        System.out.println("humidityDay");
        String city = "";
        String expResult = "HumidityDay"+city;
        String result = constants.humidityDay(city);
        assertEquals(expResult, result);
    }

    /**
     * Test of humidityHour method, of class constants.
     */
    @Test
    public void testHumidityHour() {
        System.out.println("humidityHour");
        String city = "";
        String expResult = "HumidityHour"+city;
        String result = constants.humidityHour(city);
        assertEquals(expResult, result);
    }

    /**
     * Test of temperatureDay method, of class constants.
     */
    @Test
    public void testTemperatureDay() {
        System.out.println("temperatureDay");
        String city = "";
        String expResult = "TemperatureDay"+city;
        String result = constants.temperatureDay(city);
        assertEquals(expResult, result);
    }

    /**
     * Test of temperatureHour method, of class constants.
     */
    @Test
    public void testTemperatureHour() {
        System.out.println("temperatureHour");
        String city = "";
        String expResult = "TemperatureHour"+city;
        String result = constants.temperatureHour(city);
        assertEquals(expResult, result);
    }
    
}
