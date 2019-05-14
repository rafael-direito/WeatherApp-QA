/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.restapi.mappings;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import weather_app.cache.MCache;
import weather_app.constants.Constants;
import weather_app.ipma.IpmaCalls;
import weather_app.openweather.DataType;
import weather_app.openweather.OpenWeatherCalls;

/**
 *
 * @author rd
 */
@RunWith(MockitoJUnitRunner.class)
public class TemperatureResourcesTest {
    
    public TemperatureResourcesTest() {
    }
    
    @Mock 
    OpenWeatherCalls openWeatherCallsMockito = new OpenWeatherCalls();
    @Mock 
    IpmaCalls ipmaCallsMockito = new IpmaCalls();

    TemperatureResources temperatureResources =  new TemperatureResources();
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
         MockitoAnnotations.initMocks(this);
    }
    
    @After
    public void tearDown() {
    }

    
    @Test
    public void testApiTemperaturesStatusCodeDay()
    {

        RestAssured
                .given()
                .when()
                .get("http://localhost:8080/api/temperatures/day/Lisboa")
                    .then()
                .statusCode(200)
                .and()
                    .contentType(ContentType.JSON);
    }
    
    
    @Test
    public void testTemperaturesDay()
    {   
        Response  response =
                RestAssured.given().
                        when().
                        get("http://localhost:8080/api/temperatures/day/Lisboa").
                        then().
                        extract().response();
        

        int size = response.body().path("size()");        
        assertEquals(6, size);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date).toString();
        assert(response.path(today) != null);
    }
    
    @Test
    public void testApiTemperaturesStatusCodeHours()
    {

        RestAssured
                .given()
                .when()
                .get("http://localhost:8080/api/temperatures/hour/Lisboa")
                    .then()
                .statusCode(200)
                .and()
                    .contentType(ContentType.JSON);
    }
    
    
    @Test
    public void testTemperaturesHours()
    {   
        Response  response =
                RestAssured.given().
                        when().
                        get("http://localhost:8080/api/temperatures/hour/Lisboa").
                        then().
                        extract().response();
                
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);  
        Date date = new Date();
        String newDate = sdf.format(cal.getTime()); 
        
        newDate += " 12:00:00";
        System.out.println("&&" + newDate);
        assert(response.path(newDate) != null);
    }
    
    
    @Test
    public void testGetTemperatureByDayEmptyCache() throws InterruptedException 
    {
        System.out.println("testGetTemperatureByDayEmptyCache");
        
        Map<String, Double> ipma = new HashMap<>();
         ipma.put("2019-01-01", 20.0);
         ipma.put("2019-01-02", 20.0);
         
         Map<String, Double> openWeather = new HashMap<>();
         openWeather.put("2019-01-01", 22.0);
         openWeather.put("2019-01-02", 24.0);
         openWeather.put("2019-01-03", 30.0);
         
         Map<String, Double> both = new HashMap<>();
         both.put("2019-01-01", 21.0);
         both.put("2019-01-02", 22.0);
         both.put("2019-01-03", 30.0);
            
         Mockito.when(openWeatherCallsMockito.getDataByDay("Aveiro", DataType.TEMPERATURE)).thenReturn(openWeather);
         Mockito.when(ipmaCallsMockito.getTemperatures("Aveiro")).thenReturn(ipma);
         temperatureResources.setOpenWeatherCalls(openWeatherCallsMockito);
         temperatureResources.setIpmaCalls(ipmaCallsMockito);
         
        
        temperatureResources.setmCache(new MCache(30));
        
        assertEquals(temperatureResources.getTemperatureByDay("Aveiro"),both);
    }

    
    @Test
    public void testGetTemperatureByDayCacheOk() throws InterruptedException 
    {
        System.out.println("testGetTemperatureByDayCacheOk");        
        
        Constants constants = new Constants();
        Map<String,Double> expected = new HashMap<>();
        expected.put("key", 2.0);
        
        
        MCache mCache = new MCache(30);
        mCache.add(constants.temperatureDay("Aveiro"), expected, 20);
        temperatureResources.setmCache(mCache);
        
        assertEquals(temperatureResources.getTemperatureByDay("Aveiro"), (HashMap<String, Double>) expected);
    }

    
    @Test
    public void testGetTemperatureByHourEmptyCache() throws InterruptedException 
    {
        System.out.println("testGetTemperatureByHourEmptyCache");
        Mockito.when(openWeatherCallsMockito.getDataByHour("Aveiro", DataType.TEMPERATURE)).thenReturn(new HashMap<String, Double>());
        
        temperatureResources.setOpenWeatherCalls(openWeatherCallsMockito);
        temperatureResources.setmCache(new MCache(30));
        
        assertEquals(temperatureResources.getTemperatureByHour("Aveiro"), new HashMap<String, Double>());
    }
    

    @Test
    public void testGetTemperatureByHourCacheOk() throws InterruptedException 
    {
        System.out.println("testGetTemperatureByHourCacheOk");        
        
        Constants constants = new Constants();
        Map<String,Double> expected = new HashMap<>();
        expected.put("key", 2.0);
        
        
        MCache mCache = new MCache(30);
        mCache.add(constants.temperatureHour("Aveiro"), expected, 20);
        temperatureResources.setmCache(mCache);
        
        assertEquals(temperatureResources.getTemperatureByHour("Aveiro"), (HashMap<String, Double>) expected);
    }
    

    /**
     * Test of generateTemperatureByDay method, of class TemperatureResources.
     */
    @Test
    public void testGenerateTemperatureByDay() 
    {
        
        System.out.println("testGenerateTemperatureByDay");
        
         Map<String, Double> ipma = new HashMap<>();
         ipma.put("2019-01-01", 20.0);
         ipma.put("2019-01-02", 20.0);
         
         Map<String, Double> openWeather = new HashMap<>();
         openWeather.put("2019-01-01", 22.0);
         openWeather.put("2019-01-02", 24.0);
         openWeather.put("2019-01-03", 30.0);
         
         Map<String, Double> both = new HashMap<>();
         both.put("2019-01-01", 21.0);
         both.put("2019-01-02", 22.0);
         both.put("2019-01-03", 30.0);
            
         Mockito.when(openWeatherCallsMockito.getDataByDay("Aveiro", DataType.TEMPERATURE)).thenReturn(openWeather);
         Mockito.when(ipmaCallsMockito.getTemperatures("Aveiro")).thenReturn(ipma);
         temperatureResources.setOpenWeatherCalls(openWeatherCallsMockito);
         temperatureResources.setIpmaCalls(ipmaCallsMockito);
        
         assertEquals(temperatureResources.generateTemperatureByDay("Aveiro"),both);
         
         
        // test null cases
        // ipma Weahter null
        Mockito.when(ipmaCallsMockito.getTemperatures("Aveiro")).thenReturn(null);
        assertEquals(temperatureResources.generateTemperatureByDay("Aveiro"), openWeather); 
        
        // open Weather null
        Mockito.when(ipmaCallsMockito.getTemperatures("Aveiro")).thenReturn(ipma);
        Mockito.when(openWeatherCallsMockito.getDataByDay("Aveiro", DataType.TEMPERATURE)).thenReturn(null);
        assertEquals(temperatureResources.generateTemperatureByDay("Aveiro"), ipma); 
         
        // both null
        Mockito.when(ipmaCallsMockito.getTemperatures("Aveiro")).thenReturn(null);
        assertEquals(temperatureResources.generateTemperatureByDay("Aveiro"), new HashMap<String, Double>()); 
    }
}
