
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
        assertEquals(Constants.dateToString("2019-01-01"), "January, 1st");
        assertEquals(Constants.dateToString("2019-02-02"), "February, 2nd");
        assertEquals(Constants.dateToString("2019-03-03"), "March, 3rd");
        assertEquals(Constants.dateToString("2019-04-21"), "April, 21st");
        assertEquals(Constants.dateToString("2019-05-22"), "May, 22nd");
        assertEquals(Constants.dateToString("2019-06-23"), "June, 23rd");
        assertEquals(Constants.dateToString("2019-07-31"), "July, 31st");
        assertEquals(Constants.dateToString("2019-08-12"), "August, 12th");
        assertEquals(Constants.dateToString("2019-09-12"), "September, 12th");
        assertEquals(Constants.dateToString("2019-10-12"), "October, 12th");
        assertEquals(Constants.dateToString("2019-11-12"), "November, 12th");
        assertEquals(Constants.dateToString("2019-12-12"), "December, 12th");
        //default
        assertEquals(Constants.dateToString("2019-00-12"), "-, 12th");
        
    }
    
    
    @Test
    @DisplayName("From Date to Day of The Week")
    public void checkDateToDayOfWeek()
    {
        assertEquals(Constants.dateToDayOfWeek("2019-05-12"), "Sunday");
        assertEquals(Constants.dateToDayOfWeek("2019/05/12"), "error");
    }
    
}
