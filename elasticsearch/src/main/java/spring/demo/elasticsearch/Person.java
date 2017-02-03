package spring.demo.elasticsearch;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "resource", type = "person", shards = 1, replicas = 0)
@Data

public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String firstName;
	private String lastName;

	public Person(){}
	
	public Person(String f, String l) {
	
		firstName = f;
		lastName = l;

	}

}
