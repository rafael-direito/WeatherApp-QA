
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.gen5.api.DisplayName;
import weather_app.constants.Calculations;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author rd
 */
public class TestCalculations 
{
    public TestCalculations() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    
    
    @Test
    @DisplayName("mostCommonElement")
    public void mostCommonElement()
    {
        assertEquals("a", Calculations.mostCommonElement(new String[] {"a", "b", "a"}));
    }
    
    @Test
    @DisplayName("averageDoubleFromArray")
    public void averageDoubleFromArray()
    {
        assert(2.0 == Calculations.averageDoubleFromArray(new String[] {"3", "1", "2"}));
    }
    
    

}
