package spring.demo.elasticsearch;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Document(indexName = "resource", type = "person", shards = 1, replicas = 0)
@Data

public class Person {

	@Id
	private String id;

	private String firstname;
	private String lastname;

	public Person(String id, String f, String l) {
		this.id = id;
		firstname = f;
		lastname = l;

	}

}
