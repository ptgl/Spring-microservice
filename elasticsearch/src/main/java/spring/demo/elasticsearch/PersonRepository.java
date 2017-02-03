package spring.demo.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person,String> {

	public Person findByFirstName(String firstName);
	
}
