/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.restapi.mappings;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author rd
 */
@RunWith(SpringRunner.class)
public class ApiTesting 
{
    private String baseUrl = "http://localhost:8081/api/";
            
    @BeforeClass
    public static void setUpClass() 
    {
    }
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    
    @Test
    public void testForecastStatusCode()
    {
        assert(true==false);
        
        RestAssured
            .given()
            .when()
            .get(baseUrl+ "general_info")
            .then()
                .statusCode(200);
    }
    
    @Test
    public void testForecast()
    {
        RestAssured
            .given()
            .when()
            .get(baseUrl+ "general_info/Lisboa/3")
            .then()
                .assertThat()
                .contentType(ContentType.XML);
                         
        
    }
    
}
