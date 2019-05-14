package weather_app.restapi;

import java.util.Collections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import weather_app.cache.MCache;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"weather_app.web.controllers", "weather_app.restapi.mappings"} )

public class WeatherApp{
    
    public static final Logger logger = LoggerFactory.getLogger(WeatherApp.class);
    public static final RestTemplate restTemplate = new RestTemplate();
    public static MCache mCache;
    
    public static void main(String[] args) throws InterruptedException {
        SpringApplication app = new SpringApplication(WeatherApp.class);
        app.run(args);
        try{  mCache = new MCache(30);}
        catch(Exception e){}

    }
    
}

