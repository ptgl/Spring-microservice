package spring.demo.elasticsearch;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.demo.config.ElasticsearchConfiguration;
import spring.demo.controller.UserController;








/**
 * Hello world!
 *
 */

@EnableEurekaClient
@Configuration
@ComponentScan(basePackages = "spring.demo")
@EnableAutoConfiguration(exclude = {ElasticsearchConfiguration.class})
public class App 
{
	
	private static final Logger logger = Logger.getLogger(UserController.class);

	public static void main( String[] args )
    {
    	 SpringApplication.run(App.class,args);
    	 System.out.println("ELASTIC SEARCH");
    	 logger.info("CREATED!");
    	
    	
    }
}
