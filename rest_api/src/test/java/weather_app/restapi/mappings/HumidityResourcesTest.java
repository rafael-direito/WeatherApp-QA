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
import org.assertj.core.util.DateUtil;
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
import weather_app.openweather.DataType;
import weather_app.openweather.OpenWeatherCalls;

/**
 *
 * @author rd
 */
@RunWith(MockitoJUnitRunner.class)
public class HumidityResourcesTest {
    
    public HumidityResourcesTest() {
    }
    
    
    @Mock 
    OpenWeatherCalls openWeatherCallsMockito = new OpenWeatherCalls();
    @InjectMocks
    HumidityResources humidityResources =  new HumidityResources();
    
    
    @Before
    public void setUp() throws InterruptedException {
        MockitoAnnotations.initMocks(this);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    
    @After
    public void tearDown() {
    }


    @Test
    public void testApiHumiditiesStatusCodeDay()
    {
        RestAssured
                .given()
                .when()
                .get("http://localhost:8080/api/humidities/day/Lisboa")
                    .then()
                .statusCode(200)
                .and()
                    .contentType(ContentType.JSON);
    }
    
    
    @Test
    public void testHumiditiesDay()
    {   
        Response  response =
                RestAssured.given().
                        when().
                        get("http://localhost:8080/api/humidities/day/Lisboa").
                        then().
                        extract().response();
        

        int size = response.body().path("size()");        
        assertEquals(5, size);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date).toString();
        assert(response.path(today) != null);
    }
    
    @Test
    public void testApiHumiditiesStatusCodeHours()
    {

        RestAssured
                .given()
                .when()
                .get("http://localhost:8080/api/humidities/hour/Lisboa")
                    .then()
                .statusCode(200)
                .and()
                    .contentType(ContentType.JSON);
    }
    
    
    @Test
    public void testHumiditiesHours()
    {   
        Response  response =
                RestAssured.given().
                        when().
                        get("http://localhost:8080/api/humidities/hour/Lisboa").
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
    public void testGetHumidityByDayEmptyCache() throws InterruptedException 
    {
        System.out.println("testGetHumidityByDayEmptyCache");
        Mockito.when(openWeatherCallsMockito.getDataByDay("Aveiro", DataType.HUMIDITY)).thenReturn(new HashMap<String, Double>());
        
        humidityResources.setOpenWeatherCalls(openWeatherCallsMockito);
        humidityResources.setmCache(new MCache(30));
        
        assertEquals(humidityResources.getHumidityByDay("Aveiro"), new HashMap<String, Double>());
    }

    
    @Test
    public void testGetHumidityByDayCacheOk() throws InterruptedException 
    {
        System.out.println("testGetHumidityByDayCacheOk");        
        
        Constants constants = new Constants();
        Map<String,Double> expected = new HashMap<>();
        expected.put("key", 2.0);
        
        
        MCache mCache = new MCache(30);
        mCache.add(constants.humidityDay("Aveiro"), expected, 20);
        humidityResources.setmCache(mCache);
        
        assertEquals(humidityResources.getHumidityByDay("Aveiro"), (HashMap<String, Double>) expected);
    }
    
    @Test
    public void testGetHumidityByHourEmptyCache() throws InterruptedException 
    {
        System.out.println("testGetHumidityByHourEmptyCache");
        Mockito.when(openWeatherCallsMockito.getDataByHour("Aveiro", DataType.HUMIDITY)).thenReturn(new HashMap<String, Double>());
        
        humidityResources.setOpenWeatherCalls(openWeatherCallsMockito);
        humidityResources.setmCache(new MCache(30));
        
        assertEquals(humidityResources.getHumidityByHour("Aveiro"), new HashMap<String, Double>());
    }
    

    @Test
    public void testGetHumidityByHourCacheOk() throws InterruptedException 
    {
        System.out.println("testGetHumidityByHourCacheOk");        
        
        Constants constants = new Constants();
        Map<String,Double> expected = new HashMap<>();
        expected.put("key", 2.0);
        
        
        MCache mCache = new MCache(30);
        mCache.add(constants.humidityHour("Aveiro"), expected, 20);
        humidityResources.setmCache(mCache);
        
        assertEquals(humidityResources.getHumidityByHour("Aveiro"), (HashMap<String, Double>) expected);
    }
}
