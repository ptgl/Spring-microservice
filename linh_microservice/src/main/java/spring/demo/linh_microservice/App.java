package spring.demo.linh_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories(basePackages="spring.demo")
@ComponentScan(basePackages = "spring.demo")
@EntityScan("spring.demo")
public class App 
{
    public static void main( String[] args )
    {
//    	System.setProperty("spring.config.name", "web-server");
    	SpringApplication.run(App.class, args);
    }
}
