
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
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
    
    Calculations calculations = new Calculations();
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    
    
    @Test
    public void mostCommonElement()
    {
        assertEquals("a", calculations.mostCommonElement(new String[] {"a", "b", "a"}));
    }
    
    @Test
    public void averageDoubleFromArray()
    {
        assert(2.0 == calculations.averageDoubleFromArray(new String[] {"3", "1", "2"}));
    }
    
    

}
