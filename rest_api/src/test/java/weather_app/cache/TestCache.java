/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weather_app.cache;

import java.util.concurrent.Semaphore;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import weather_app.constants.Converters;

/**
 *
 * @author rd
 */
public class TestCache 
{
    public TestCache() {}
    
    @BeforeClass
    public static void setUpClass() {}
    
    @AfterClass
    public static void tearDownClass() {}
    
    @Before
    public void setUp() {}
    
    @After
    public void tearDown() {}
    
    // TESTS ------------------------------------------------------------------
    

    @Test
    public void addCache() throws InterruptedException
    {    
        MCache mCache = new MCache(30);
        
        mCache.add("key", "value", 90);
        assertEquals("value", (String) mCache.get("key"));
        
        mCache.add("key", null, 90);
        assertEquals(null, mCache.get("key"));
        
        mCache.add(null, "value", 90);        
    }
    
    
    @Test
    public void getCache() throws InterruptedException
    {        
        MCache mCache = new MCache(30);
        
        mCache.add("key", "value", 1);
        Thread.sleep(2000);
        assertEquals(null, mCache.get("key"));
    }
    
    
    @Test
    public void deleteCache() throws InterruptedException
    {        
        MCache mCache = new MCache(30);
        mCache.delete("key");
        assertEquals(false, mCache.delete("key"));
    }
    
    
    @Test
    public void clearCache() throws InterruptedException
    {        
        MCache mCache = new MCache(30);
        mCache.add("key", "value", 100);
        mCache.clear();
        assertEquals(0, mCache.size());
    }
    
    
    @Test
    public void cleaningThread() throws InterruptedException
    {       
        Semaphore sem = new Semaphore(1); 
        
        MCache mCache = new MCache(1);
        mCache.add("key", "value", 2);
        Thread.sleep(1500);
        mCache.add("key2", "value", 1);
        Thread.sleep(1500);
        
        assertEquals(null, mCache.get("key1"));
        
        mCache.add("key", "value", 1);
        

        //sem.acquire();
        //mCache.getCleaningThread().interrupt();
        //ssertEquals(true, mCache.getCleaningThread().isInterrupted());
        //sem7.release();

    }
    
    @Test
    public void hitsCache() throws InterruptedException
    {        
        MCache mCache = new MCache(30);
        mCache.add("key", "value", 100);
        mCache.get("key");
        
        assertEquals(1, mCache.getHits());
    }
    
    
    @Test
    public void missesCache() throws InterruptedException
    {        
        MCache mCache = new MCache(30);
        mCache.get("key");
        assertEquals(1, mCache.getMisses());
    }
    
    @Test
    public void cleanupTimeCache() throws InterruptedException
    {        
        MCache mCache = new MCache(30);
        assertEquals(30, mCache.getCleanUpTimeSeconds());
    }


    
 
    
    
    
    
}
