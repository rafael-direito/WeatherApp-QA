
import constants.Constants;
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
public class TestConverters 
{
    public TestConverters() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    
    
    @Test
    @DisplayName("From Date to String")
    public void checkDateToString()
    {
        String mDate = "2019-05-12";
        String expected = "May, 12th";
        assertEquals(Constants.dateToString(mDate), expected);
    }
    
    
    @Test
    @DisplayName("From Date to Day of The Week")
    public void checkDateToDayOfWeek()
    {
        String mDate = "2019-05-12";
        String expected = "Sunday";
        assertEquals(Constants.dateToDayOfWeek(mDate), expected);
    }
    
}
