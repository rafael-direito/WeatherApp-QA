package pt.ua.weather_app.weather_app;

import weather_app.restapi.WeatherApp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WeatherApp.class)
public class WeatherAppTests {

	@Test
	public void contextLoads() {
	}

}
