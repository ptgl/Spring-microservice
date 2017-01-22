package spring.demo.linh_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;



/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
public class App 
{
    public static void main( String[] args )
    {
//    	System.setProperty("spring.config.name", "web-server");
    	SpringApplication.run(App.class, args);
    }
}
