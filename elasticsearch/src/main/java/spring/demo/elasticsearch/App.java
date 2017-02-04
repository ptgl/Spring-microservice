package spring.demo.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import spring.demo.config.ElasticsearchConfiguration;








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

	public static void main( String[] args )
    {
    	 SpringApplication.run(App.class,args);
    	 System.out.println("ELASTIC SEARCH");
    	
    }
}
