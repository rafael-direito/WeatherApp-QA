/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package weather_app.cache;

import java.util.HashMap;
import java.util.Map;
import static weather_app.restapi.WeatherApp.logger;

/**
 *
 * @author rd
 */
public class MCache
{
    private int hits=0;
    private int misses=0;
    private Thread cleaningThread=null;
    private Map<String, Object> cache = new HashMap<>();
    private Map<String, Long> deleteTimes = new HashMap<>();
    
    // there is thread that will run from n to n seconds and check if a registry
    // can be deleted
    private long cleanUpTimeSeconds;
    
    public MCache (long cleanUpTimeSeconds) throws InterruptedException
    {
        this.cleanUpTimeSeconds=cleanUpTimeSeconds;
        //initiate cleaning thread
        cleaningThread = initiateCleaningThread();
        
    }

    public boolean add(String key, Object value, long ttl)
    {
        if (key == null) {return false;}
        
        if (value == null)
            delete(key);
        else
        {
            long deleteTime = System.currentTimeMillis() + ttl * 1000;
            deleteTimes.put(key, deleteTime);
            cache.put(key, value);
            return true;
        }
        return false;
    }
    
    
    public boolean delete(String key)
    {
        if (!cache.containsKey(key))
            return false;
        else
        {
            cache.remove(key);
            deleteTimes.remove(key);
            return true;
        }
    }
    
    
    public Object get(String key)
    {
        if(cache.containsKey(key) && deleteTimes.get(key)>System.currentTimeMillis())
        {
            logger.info("Information extracted from cache");
            hits++;
            return cache.get(key);
        }
        misses++;
        return null;
    }
    
    
    public void clear() { cache.clear();}
    
    public int size() { return cache.size(); }
    
    public Thread initiateCleaningThread()throws InterruptedException
    {
        Thread thread = new Thread()
        {
            @Override
            public void run()
            {
                while(true)
                {
                    logger.info("CleaningThread is now executing");
                    
                    for(String k : cache.keySet())
                    {
                        if(deleteTimes.get(k) <  System.currentTimeMillis())
                        {
                            logger.info( "Deleted key: " + k);
                            delete(k);
                        }
                    }
                    try {
                        Thread.sleep(cleanUpTimeSeconds * 1000);
                    } catch (InterruptedException ex) {
                        logger.error("CleaningThread Interrupted");
                        Thread.currentThread().interrupt();
                    }
                }
            }
            
        };
        
        thread.start();
        return thread;
    }
    

    public int getHits() {
        return hits;
    }

    public int getMisses() {
        return misses;
    }

    public long getCleanUpTimeSeconds() {
        return cleanUpTimeSeconds;
    }

    public Thread getCleaningThread() {
        return cleaningThread;
    }
    
    
}
