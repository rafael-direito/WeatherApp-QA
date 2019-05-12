
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
public class TestStaticBuilders 
{
    public TestStaticBuilders() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    
    
    @Test
    @DisplayName("BuildSpecificInfoPath")
    public void checkBuildSpecificInfoPath()
    {
        String day = "2019-05-12";
        String city = "Aveiro";
        String expected = Constants.BASE_API + "specific_info/Aveiro/2019-05-12";
        assertEquals(Constants.buildSpecificInfoPath(city, day), expected);
    }
    
    
    @Test
    @DisplayName("BuildGeneralInfoPath")
    public void checkBuildGeneralInfoPath()
    {
        String numDays = "4";
        String city = "Aveiro";
        String expected = Constants.BASE_API + "general_info/Aveiro/4";
        assertEquals(Constants.buildGeneralInfoPath(city, numDays), expected);
    }
    
}
