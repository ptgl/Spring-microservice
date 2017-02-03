package spring.demo.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;








/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan(basePackages = "spring.demo.elasticsearch")
@EnableAutoConfiguration(exclude = {ElasticsearchConfiguration.class})
public class App 
{

	public static void main( String[] args )
    {
    	 SpringApplication.run(App.class,args);
    	 System.out.println("ELASTIC SEARCH");
    	
    }
}
