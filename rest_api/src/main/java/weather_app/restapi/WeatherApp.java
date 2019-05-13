package weather_app.restapi;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import weather_app.cache.MCache;

@SpringBootApplication
@ComponentScan(basePackages = {"weather_app.web.controllers"} )

public class WeatherApp{
    
    public static final Logger logger = LoggerFactory.getLogger(WeatherApp.class);
    public static final RestTemplate restTemplate = new RestTemplate();
    
    public static MCache mCache = null;
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(WeatherApp.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
        try{  mCache = new MCache(30);}
        catch(Exception e){}

    }
    
}

