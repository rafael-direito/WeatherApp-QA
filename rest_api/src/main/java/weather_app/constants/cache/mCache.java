/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package weather_app.constants.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static weather_app.restapi.WeatherApp.logger;

/**
 *
 * @author rd
 */
public class mCache
{
    private Map<String, Object> mCache = new HashMap<>();
    private Map<String, Long> deleteTimes = new HashMap<>();
    
    // there is thread that will run from n to n seconds and check if a registry
    // can be deleted
    private int cleanUpTimeSeconds;
    
    public mCache (int cleanUpTimeSeconds)
    {
        this.cleanUpTimeSeconds=cleanUpTimeSeconds;
        //initiate cleaning thread
        initiateCleaningThread();
        
    }
    
    public void add(String key, Object value, long ttl)
    {
        if (key == null) {return;}
        
        if (value == null)
            delete(key);
        else
        {
            long deleteTime = System.currentTimeMillis() + ttl * 1000;
            deleteTimes.put(key, deleteTime);
            mCache.put(key, value);
        }
    }
    
    
    public boolean delete(String key)
    {
        if (!mCache.containsKey(key))
            return true;
        else
            try
            {
                mCache.remove(key);
                deleteTimes.remove(key);
                return true;
            }
            catch(Exception e) { return false; }
    }
    
    
    public Object get(String key)
    {
        if(mCache.containsKey(key) && deleteTimes.get(key)>System.currentTimeMillis())
        {
            logger.info("Information extracted from cache");
            return mCache.get(key);
        }
        return null;
    }
    
    
    public void clear() { mCache.clear();}
    
    public int size() { return mCache.size(); }
    
    public void initiateCleaningThread()
    {
        Thread thread = new Thread()
        {
            public void run()
            {
                while(true)
                {
                    logger.info("CleaningThread is now executing");
                    
                    for(String k : mCache.keySet())
                    {
                        if(deleteTimes.containsKey(k) && deleteTimes.get(k) <  System.currentTimeMillis())
                        {
                            logger.info("Deleted key: " + k);
                            delete(k);
                        }
                    }
                    try { Thread.sleep(cleanUpTimeSeconds * 1000);}
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(mCache.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
        };
        
        thread.start();
    }
    
}
