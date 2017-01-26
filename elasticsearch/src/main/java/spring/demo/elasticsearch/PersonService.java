package spring.demo.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepository;
	
	
	//@Override
    public Person save(Person post) {
    	personRepository.save(post);
        return post;
    }

    //@Override
    public Person findOne(String id) {
        return personRepository.findOne(id);
    }

    //@Override
    public Iterable<Person> findAll() {
        return personRepository.findAll();
    }

	
}
