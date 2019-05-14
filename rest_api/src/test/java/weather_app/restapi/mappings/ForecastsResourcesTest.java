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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import static org.hamcrest.Matchers.is;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import weather_app.cache.MCache;
import weather_app.constants.Constants;
import weather_app.ipma.IpmaCalls;
import weather_app.openweather.DataType;
import weather_app.openweather.OpenWeatherCalls;

/**
 *
 * @author rd
 */
public class ForecastsResourcesTest {
    
    public ForecastsResourcesTest() {
    }
    
    @InjectMocks
            ForecastsResources forecastsResources = new ForecastsResources();
    @Mock
            OpenWeatherCalls openWeatherCallsMockito = new OpenWeatherCalls();
    @Mock
            IpmaCalls ipmaCallsMockito = new IpmaCalls();
    
    private String baseUrl = "localhost:8080/api/";
    
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
    public void testGeneralForecastStatusCode()
    {
        RestAssured
                .given()
                .when()
                .get("http://localhost:8080/api/general_info/Aveiro/3")
                    .then()
                .statusCode(200)
                .and()
                    .contentType(ContentType.JSON);
    }
    
    @Test
    public void testGeneralForecast()
    {   
        Response  response =
                RestAssured.given().
                        when().
                        get("http://localhost:8080/api/general_info/Aveiro/3").then().
                        extract().response();
        

        int size = response.body().path("size()");        
        assertEquals(3, size);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date).toString();
        assert(response.path(today) != null);
    }
    
    
    @Test
    public void testSpecificForecastStatusCode()
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date).toString();
        
        System.out.println("333" +  "http://localhost:8080/api/general_info/Lisboa/" + today);
        RestAssured
                .given()
                .when()
                .get("http://localhost:8080/api/specific_info/Lisboa/" + today)
                    .then()
                .statusCode(200)
                .and()
                    .contentType(ContentType.JSON);
    }
    
    @Test
    public void testSpecificForecast()
    {   
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String today = dateFormat.format(date).toString();
        
        Response  response =
                RestAssured.given().
                        when().
                        get("http://localhost:8080/api/specific_info/Aveiro/" + today ).then().
                        extract().response();
        

        int size = response.body().path("size()");        
        assert(size==9 || size==10 || size==11);
        
        assert(response.path("humidity") != null);
        assert(response.path("longitude") != null);
    }
    
    
    @Test
    public void tesGeneralInfoCacheOk() throws InterruptedException
    {
        System.out.println("testGeneralInfoCacheOk");
        Constants constants = new Constants();
        
        
        Map<String, Map<String,String>> tmp = new HashMap<>();
        Map<String,String> expected = new HashMap<>();
        expected.put("key", "value");
        tmp.put("day", expected);
        
        
        MCache mCache = new MCache(30);
        mCache.add(constants.forecastKeyCache("Aveiro"), tmp, 20);
        forecastsResources.setmCache(mCache);
        
        assertEquals(forecastsResources.generalInfo("Aveiro",1).get("day"), (HashMap<String, String>) expected);
    }
    
    
    @Test
    public void testGenerakInfoCacheEmpty() throws InterruptedException
    {
        System.out.println("testGenerakInfoCacheEmpty");
        
        Map<String, Map<String,String>> ipma = new HashMap<>();
        Map<String,String> tmpIpma = new HashMap<>();
        tmpIpma.put("tMin", "10");
        tmpIpma.put("tMax", "15");
        tmpIpma.put("weather", "Clear Sky");
        tmpIpma.put("windDir", "N");
        tmpIpma.put("windIntensity", "10");
        tmpIpma.put("latitude", "111");
        tmpIpma.put("longitude", "222");
        tmpIpma.put("precipitationProb", "10");
        tmpIpma.put("pressure", "1000");
        tmpIpma.put("humidity", "1000");
        ipma.put("2019-01-01", tmpIpma);
        
        Map<String, Map<String,String>> open = new HashMap<>();
        Map<String,String> tmpOpen = new HashMap<>();
        tmpOpen.put("tMin", "10");
        tmpOpen.put("tMax", "15");
        tmpOpen.put("weather", "Clear Sky");
        tmpOpen.put("windDir", "N");
        tmpOpen.put("windIntensity", "10");
        tmpOpen.put("latitude", "111");
        tmpOpen.put("longitude", "222");
        tmpOpen.put("precipitationProb", "10");
        tmpOpen.put("pressure", "1000");
        tmpOpen.put("humidity", "1000");
        open.put("2019-01-02", tmpOpen);
        
        Mockito.when(ipmaCallsMockito.getForecast("Aveiro")).thenReturn(ipma);
        Mockito.when(openWeatherCallsMockito.getForecast("Aveiro")).thenReturn(open);
        
        Map<String, Map<String,String>> expected = new HashMap<>();
        expected.put("2019-01-01", tmpIpma);
        expected.put("2019-01-02", tmpOpen);
        
        
        MCache mCache = new MCache(30);
        forecastsResources.setmCache(mCache);
        
        assertEquals(expected, forecastsResources.generalInfo("Aveiro", 2));
    }
    
    
    
    @Test
    public void testSpecificInfoCacheOk() throws InterruptedException
    {
        System.out.println("testSpecificInfoCacheOk");
        
        Constants constants = new Constants();
        
        
        Map<String, Map<String,String>> tmp = new HashMap<>();
        Map<String,String> expected = new HashMap<>();
        expected.put("key", "value");
        tmp.put("day", expected);
        
        
        MCache mCache = new MCache(30);
        mCache.add(constants.forecastKeyCache("Aveiro"), tmp, 20);
        forecastsResources.setmCache(mCache);
        
        assertEquals(forecastsResources.specificInfo("Aveiro", "day"), (HashMap<String, String>) expected);
    }
    
    
    @Test
    public void testSpecificInfoCacheEmpty() throws InterruptedException
    {
        System.out.println("testSpecificInfoCacheEmpty");
        
        Map<String, Map<String,String>> ipma = new HashMap<>();
        Map<String,String> tmpIpma = new HashMap<>();
        tmpIpma.put("tMin", "10");
        tmpIpma.put("tMax", "15");
        tmpIpma.put("weather", "Clear Sky");
        tmpIpma.put("windDir", "N");
        tmpIpma.put("windIntensity", "10");
        tmpIpma.put("latitude", "111");
        tmpIpma.put("longitude", "222");
        tmpIpma.put("precipitationProb", "10");
        tmpIpma.put("pressure", "1000");
        tmpIpma.put("humidity", "1000");
        ipma.put("2019-01-01", tmpIpma);
        
        Map<String, Map<String,String>> open = new HashMap<>();
        Map<String,String> tmpOpen = new HashMap<>();
        tmpOpen.put("tMin", "10");
        tmpOpen.put("tMax", "15");
        tmpOpen.put("weather", "Clear Sky");
        tmpOpen.put("windDir", "N");
        tmpOpen.put("windIntensity", "10");
        tmpOpen.put("latitude", "111");
        tmpOpen.put("longitude", "222");
        tmpOpen.put("precipitationProb", "10");
        tmpOpen.put("pressure", "1000");
        tmpOpen.put("humidity", "1000");
        open.put("2019-01-02", tmpOpen);
        
        Mockito.when(ipmaCallsMockito.getForecast("Aveiro")).thenReturn(ipma);
        Mockito.when(openWeatherCallsMockito.getForecast("Aveiro")).thenReturn(open);
        
        Map<String, Map<String,String>> expected = new HashMap<>();
        expected.put("2019-01-01", tmpIpma);
        expected.put("2019-01-02", tmpOpen);
        
        
        MCache mCache = new MCache(30);
        forecastsResources.setmCache(mCache);
        
        assertEquals(expected, forecastsResources.mergeIpmaOpenWeather("Aveiro"));
    }
    
    
    @Test
    public void testMergeIpmaOpenWeather() throws InterruptedException
    {
        System.out.println("testMergeIpmaOpenWeather");
        
        Map<String, Map<String,String>> ipma = new HashMap<>();
        Map<String,String> tmpIpma = new HashMap<>();
        tmpIpma.put("tMin", "10");
        tmpIpma.put("tMax", "15");
        tmpIpma.put("weather", "Clear Sky");
        tmpIpma.put("windDir", "N");
        tmpIpma.put("windIntensity", "10");
        tmpIpma.put("latitude", "111");
        tmpIpma.put("longitude", "222");
        tmpIpma.put("precipitationProb", "10");
        tmpIpma.put("pressure", "1000");
        tmpIpma.put("humidity", "1000");
        ipma.put("2019-01-01", tmpIpma);
        
        Map<String, Map<String,String>> open = new HashMap<>();
        Map<String,String> tmpOpen = new HashMap<>();
        tmpOpen.put("tMin", "10");
        tmpOpen.put("tMax", "15");
        tmpOpen.put("weather", "Clear Sky");
        tmpOpen.put("windDir", "N");
        tmpOpen.put("windIntensity", "10");
        tmpOpen.put("latitude", "111");
        tmpOpen.put("longitude", "222");
        tmpOpen.put("precipitationProb", "10");
        tmpOpen.put("pressure", "1000");
        tmpOpen.put("humidity", "1000");
        open.put("2019-01-02", tmpOpen);
        
        Mockito.when(ipmaCallsMockito.getForecast("Aveiro")).thenReturn(ipma);
        Mockito.when(openWeatherCallsMockito.getForecast("Aveiro")).thenReturn(open);
        
        MCache mCache = new MCache(30);
        forecastsResources.setmCache(mCache);
        
        assertEquals(tmpIpma, forecastsResources.specificInfo("Aveiro", "2019-01-01"));
    }
    
    
    @Test
    public void testFillMap()
    {
        System.out.println("testFillMap");
        
        
        Map<String,String> mMap = new HashMap<>();
        
        Map<String, Map<String,String>> ipma = new HashMap<>();
        Map<String,String> tmp2 = new HashMap<>();
        
        tmp2.put("ola2", "huhm2");
        ipma.put("key2", tmp2);
        
        
        Map<String, Map<String,String>> open = new HashMap<>();
        Map<String,String> tmp3 = new HashMap<>();
        
        tmp3.put("ola3", "huhm3");
        open.put("key3", tmp3);
        
        // none is null
        forecastsResources.fillMap(mMap, ipma, open, "key2", "ola2" );
        assertEquals(mMap, ipma.get("key2"));
        
        // ipma is null
        mMap = new HashMap<>();
        forecastsResources.fillMap(mMap, ipma, open, "key3", "ola3" );
        assertEquals(mMap, tmp3);
        
        //both null
        mMap = new HashMap<>();
        forecastsResources.fillMap(mMap, ipma, open, "hi", "lol" );
        Map<String,String> expected = new HashMap<>();
        expected.put("lol", "none");
        assertEquals(mMap, expected);
        
        System.out.println("nMap : " + mMap);
    }
    
    
    @Test
    public void testIsEligible()
    {
        System.out.println("testIsEligible");
        
        Map<String, Map<String,String>> mMap = new HashMap<>();
        Map<String,String> tmp = new HashMap<>();
        
        tmp.put("ola", "huhm");
        mMap.put("key", tmp);
        
        assertEquals(true, forecastsResources.isEligible(mMap, "key", "ola"));
        assertEquals(false, forecastsResources.isEligible(mMap, "xx", "ola"));
    }
    
    
    
    
}
