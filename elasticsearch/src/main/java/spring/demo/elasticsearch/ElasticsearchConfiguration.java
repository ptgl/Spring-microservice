package spring.demo.elasticsearch;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.*;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;


 
@Configuration
@PropertySource(value = "classpath:elasticsearch.properties")
@EnableElasticsearchRepositories(basePackages = "spring.demo")
public class ElasticsearchConfiguration {
    @Resource
    private Environment environment;
   /* @Bean
    public Client client() {
        TransportClient client = new TransportClient();
        TransportAddress address = new InetSocketTransportAddress(environment.getProperty("elasticsearch.host"), Integer.parseInt(environment.getProperty("elasticsearch.port")));
        client.addTransportAddress(address);
        return client;
    }*/

    @Bean
    public Client client() throws UnknownHostException {
		String host = environment.getProperty("elasticsearch.host");
    	int port = Integer.parseInt(environment.getProperty("elasticsearch.port"));
    	String cluster = environment.getProperty("cluster.name");
    	
    	Settings settings = ImmutableSettings.settingsBuilder()
    	        .put("cluster.name", cluster)
    	        .build();
//    	Client client = TransportClient.builder().settings(settings).build()
//    			 .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));
    	TransportClient client = new PreBuiltTransportClient(settings)
    			.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(host), port));;
    	
    	/*Settings settings = ImmutableSettings.builder()
    	        .put("cluster.name", "myClusterName").build();
    	TransportClient client = new PreBuiltTransportClient(settings);
    	*/
    	
        return client;
    }
    
    
    
    
    @Bean
    public ElasticsearchOperations elasticsearchTemplate() throws UnknownHostException {
        return new ElasticsearchTemplate(client());
    }


}
