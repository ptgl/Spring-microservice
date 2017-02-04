package spring.demo.sample;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person,String> {

	public Person findByFirstName(String firstName);
	
}
