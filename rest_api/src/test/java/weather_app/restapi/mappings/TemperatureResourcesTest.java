/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.restapi.mappings;

import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import weather_app.cache.MCache;
import weather_app.restapi.WeatherApp;
import static weather_app.restapi.WeatherApp.mCache;

/**
 *
 * @author rd
 */
public class TemperatureResourcesTest {
    
    @Mock
    MCache mCache;
    
    @InjectMocks
    TemperatureResources instance;
    
            
    public TemperatureResourcesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws InterruptedException 
    {
       mCache = new MCache(30);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTemperatureByDay method, of class TemperatureResources.
     */
    @Test
    public void testGetTemperatureByDay() 
    {
        System.out.println("getTemperatureByDay");
        String city = "";
        System.out.println(instance.generateTemperatureByDay("Aveiro"));
        System.out.println(instance.getTemperatureByDay("Aveiro"));
        /*        
        Map<String, Double> expResult = null;
        Map<String, Double> result = instance.getTemperatureByDay(city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }

    /**
     * Test of getTemperatureByHour method, of class TemperatureResources.
     */
    @Test
    public void testGetTemperatureByHour() {
        /*
        System.out.println("getTemperatureByHour");
        String city = "";
        TemperatureResources instance = new TemperatureResources();
        Map<String, Double> expResult = null;
        Map<String, Double> result = instance.getTemperatureByHour(city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        */
    }
    
}
