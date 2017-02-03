package spring.demo.elasticsearch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonController {

	@Autowired
	PersonService service;

	@Autowired
	PersonRepository repository;

	@RequestMapping(method = RequestMethod.GET, value = "/person")
	public @ResponseBody ResponseEntity<?> getModels() throws Exception {

		repository.deleteAll();

		Person p = new Person("linh", "phan");
		p.setId("01");
		service.save(p);
		Person p1 = new Person("hoa", "nguyen");
		p.setId("02");
		service.save(p1);

		return new ResponseEntity(HttpStatus.OK);// ResponseEntity.ok(p);
	}

	// find by id
	@RequestMapping(method = RequestMethod.GET, value = "/person/{ID}")
	public @ResponseBody ResponseEntity<?> findbyID(@PathVariable(value = "ID") String id) {
		Person p =  service.findOne(id);
		return new ResponseEntity<Person>(p, HttpStatus.OK);
	}

	// Find all
	@RequestMapping(method = RequestMethod.GET, value = "/person/all")
	public @ResponseBody ResponseEntity<?> getAll() {
		Page<Person> p = (Page<Person>) service.findAll();
		return new ResponseEntity<Page<Person>>(p, HttpStatus.OK);
	}
	
//	// find by name
//		@RequestMapping(method = RequestMethod.GET, value = "/person/{name}")
//		public @ResponseBody ResponseEntity<?> findbyID(@PathVariable(value = "name") String name) {
//			Person p = repository.findByFirstName(name);
//			System.out.println(p);
//			return new ResponseEntity<Person>(p, HttpStatus.OK);
//		}

}
