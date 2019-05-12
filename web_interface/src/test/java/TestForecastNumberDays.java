
import constants.Constants;
import forms.ForecastNumberDays;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.gen5.api.DisplayName;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rd
 */
public class TestForecastNumberDays 
{
    public TestForecastNumberDays() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    
    
    @Test
    @DisplayName("ForecastNumberDays")
    public void checkDateToString()
    {
        //empty constructor test
        ForecastNumberDays fn = new ForecastNumberDays();
        assertEquals(fn.getCity(), "");
        assertEquals(fn.getNumDays(), 0);
        
        // test city
        fn.setCity("Aveiro");
        assertEquals(fn.getCity(), "Aveiro");
        
        //test numDays
        fn.setNumDays(2);
        assertEquals(fn.getNumDays(), 2);
    }
}
