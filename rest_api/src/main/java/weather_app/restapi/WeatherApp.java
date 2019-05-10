package weather_app.restapi;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;
import weather_app.data.ipma.GeneralForecastIpma;

@SpringBootApplication
public class WeatherApp {
    
    public static RestTemplate restTemplate;
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WeatherApp.class);
        app.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        app.run(args);
        
        restTemplate = new RestTemplate();
        
        
    }
    
}

