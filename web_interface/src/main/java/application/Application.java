package application;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
 *
 * @author rd
 */
@SpringBootApplication
@ComponentScan(basePackages = {"controllers", "forms"} )
public class Application 
{
    public static final Logger logger = LoggerFactory.getLogger(Application.class);
    public static final RestTemplate restTemplate = new RestTemplate();
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
}