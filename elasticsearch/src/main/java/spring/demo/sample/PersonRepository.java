package spring.demo.sample;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface PersonRepository extends ElasticsearchRepository<Person,String> {

	
	public Person findByFirstName(String firstName);
	
	@Query("{\"bool\" : {\"must\" : {\"term\" : {\"lastName\" : \"?0\"}}}}")
	public Person findByName(String firstName);
	
}
