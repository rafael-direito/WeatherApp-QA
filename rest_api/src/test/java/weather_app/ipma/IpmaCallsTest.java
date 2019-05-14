/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.ipma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
import org.springframework.web.client.RestTemplate;
import weather_app.cache.MCache;
import x.WeatherTypeIpma;
import x.WeatherTypeResponseIpma;
import x.WindTypeIpma;
import x.WindTypeResponseIpma;


/**
 *
 * @author rd
 */
public class IpmaCallsTest {
    
    public IpmaCallsTest() {
    }
    
    @Mock 
    RestTemplate restTemplateMock = new RestTemplate();
    @InjectMocks
    IpmaCalls ipmaCalls =  new IpmaCalls();
    
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

    /**
     * Test of getTemperatures method, of class IpmaCalls.
     */
    
    @Test
    public void getBasicInfo() 
    {
        System.out.println("getBasicInfo");
        
        PortugalDistrictsIpma districts = new PortugalDistrictsIpma();
        List<DistrictIpma> list = new ArrayList<>();
        
        DistrictIpma distric = new DistrictIpma();
        distric.setGlobalIdLocal(1);
        distric.setLocal("Aveiro");
        
        list.add(distric);
        districts.setData(list);
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/distrits-islands.json", PortugalDistrictsIpma.class)).thenReturn(districts);
       
        GeneralForecastIpma forecastIpma = new GeneralForecastIpma();
        forecastIpma.setOwner("rd");
                
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/"+1+".json", GeneralForecastIpma.class)).thenReturn(forecastIpma);
        
        ipmaCalls.setRestTemplate(restTemplateMock);
        
        assertEquals("rd", ipmaCalls.getBaseInfo("Aveiro").getOwner());
   
    }
    
    
    
    @Test
    public void getWeatherType() 
    {
        System.out.println("getWeatherType");
        
        WeatherTypeResponseIpma response = new WeatherTypeResponseIpma();
        
        List<WeatherTypeIpma> list = new ArrayList<>();
        
        WeatherTypeIpma ipma = new WeatherTypeIpma();
        ipma.setIdWeatherType(1);
        ipma.setDescIdWeatherTypeEN("hello");
        

        
        list.add(ipma);
        response.setData(list);
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/weather-type-classe.json", WeatherTypeResponseIpma.class)).thenReturn(response);       
        ipmaCalls.setRestTemplate(restTemplateMock);
             
        assertEquals(ipma, ipmaCalls.getWeatherType(1));
        assertEquals(null, ipmaCalls.getWeatherType(2));
   
    }
    
    
    @Test
    public void getWindType() 
    {
        System.out.println("getWindType");
        
        WindTypeResponseIpma response = new WindTypeResponseIpma();
        
        List<WindTypeIpma> list = new ArrayList<>();
        
        WindTypeIpma ipma = new WindTypeIpma();
        ipma.setClassWindSpeed("1");
       
        
        list.add(ipma);
        response.setData(list);
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/wind-speed-daily-classe.json", WindTypeResponseIpma.class)).thenReturn(response);       
        ipmaCalls.setRestTemplate(restTemplateMock);
             
        assertEquals(ipma, ipmaCalls.getWindType(1));
        assertEquals(null, ipmaCalls.getWindType(2));
    }

 
    
    @Test
    public void testGetTemperatures() 
    {             
        PortugalDistrictsIpma districts = new PortugalDistrictsIpma();
        List<DistrictIpma> list2 = new ArrayList<>();
        
        DistrictIpma distric = new DistrictIpma();
        distric.setGlobalIdLocal(1);
        distric.setLocal("Aveiro");
        
        list2.add(distric);
        districts.setData(list2);
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/distrits-islands.json", PortugalDistrictsIpma.class)).thenReturn(districts);
        
        //--------
        GeneralForecastIpma gf = new GeneralForecastIpma();
        
        List<DailyForecastIpma> list = new ArrayList<>();
        
        DailyForecastIpma d1 = new DailyForecastIpma();
        d1.setTMin("10");
        d1.setTMax("20");
        d1.setForecastDate("2019-05-01");
        
        DailyForecastIpma d2 = new DailyForecastIpma();
        d2.setTMin("5");
        d2.setTMax("10");
        d2.setForecastDate("2019-05-02");
        
        list.add(d1);
        list.add(d2);
        
        gf.setData(list);
        
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1.json", GeneralForecastIpma.class)).thenReturn(gf);
        
        
        Map<String, Double> ipmaInfo = new TreeMap<>();
        ipmaInfo.put("2019-05-01",15.0);
        ipmaInfo.put("2019-05-02",7.5);
         
        System.out.println(ipmaCalls.getBaseInfo("Aveiro"));
        
        assertEquals(ipmaInfo, ipmaCalls.getTemperatures("Aveiro"));
        
        
        
        // throw exception
        list.add(null);
        gf.setData(list);
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1.json", GeneralForecastIpma.class)).thenReturn(gf);

        assertEquals(null, ipmaCalls.getTemperatures("Aveiro"));
    }
    
    
    
     @Test
    public void testGetforecast() 
    { 
        PortugalDistrictsIpma districts = new PortugalDistrictsIpma();
        List<DistrictIpma> list2 = new ArrayList<>();
        
        DistrictIpma distric = new DistrictIpma();
        distric.setGlobalIdLocal(1);
        distric.setLocal("Aveiro");
        
        list2.add(distric);
        districts.setData(list2);
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/distrits-islands.json", PortugalDistrictsIpma.class)).thenReturn(districts);
        
        //--------
        GeneralForecastIpma gf = new GeneralForecastIpma();
        
        List<DailyForecastIpma> list = new ArrayList<>();
        
        DailyForecastIpma d1 = new DailyForecastIpma();
        d1.setTMin("10");
        d1.setTMax("20");
        d1.setPrecipitaProb("90");
        d1.setLatitude("1111");
        d1.setLongitude("2222");
        d1.setForecastDate("2019-05-01");
        d1.setPredWindDir("N");
        d1.setIdWeatherType(1); //Clear Sky
        d1.setClassWindSpeed(1); //Weak

        list.add(d1);
        gf.setData(list);
        
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1.json", GeneralForecastIpma.class)).thenReturn(gf);  
        
        WeatherTypeResponseIpma weatherTypeResponse = new WeatherTypeResponseIpma();
        WeatherTypeIpma weatherIpma = new WeatherTypeIpma();
        weatherIpma.setIdWeatherType(1);
        weatherIpma.setDescIdWeatherTypeEN("Clear Sky");
        List<WeatherTypeIpma> listWeather = new ArrayList<>();
        listWeather.add(weatherIpma);
        weatherTypeResponse.setData(listWeather);
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/weather-type-classe.json", WeatherTypeResponseIpma.class)).thenReturn(weatherTypeResponse);  

         
        WindTypeResponseIpma windTypeResponseIpma = new WindTypeResponseIpma();
        WindTypeIpma windTypeIpma = new WindTypeIpma();
        windTypeIpma.setClassWindSpeed("1");
        windTypeIpma.setDescClassWindSpeedDailyEN("Weak");
        
        List<WindTypeIpma> listWind = new ArrayList<>();
        listWind.add(windTypeIpma);
        windTypeResponseIpma.setData(listWind);
        
        Mockito.when(restTemplateMock.getForObject("http://api.ipma.pt/open-data/wind-speed-daily-classe.json", WindTypeResponseIpma.class)).thenReturn(windTypeResponseIpma);  
        
  
        Map<String, String> tmp = new HashMap<>();
        tmp.put("tMin", "10");
        tmp.put("tMax", "20");
        tmp.put("precipitationProb", "90");
        tmp.put("latitude", "1111");
        tmp.put("longitude", "2222");
        tmp.put("windDir", "N");
        tmp.put("weather", "Clear Sky");
        tmp.put("windIntensity", "Weak");
        
        Map<String, Map<String, String>> ipmaInfo = new TreeMap<>();
        ipmaInfo.put("2019-05-01", tmp);
        
        assertEquals(ipmaInfo, ipmaCalls.getForecast("Aveiro"));
        assertEquals(null, ipmaCalls.getForecast("Null"));
    }
    
}
