package spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.demo.linh_model.*;

import spring.demo.repository.UserRepository;


@Controller
public class UserController {

	@Autowired
	private UserRepository repository;

	/*@Autowired
	private UserServiceImpl userService;*/

	// Get empty model
	@RequestMapping(method = RequestMethod.GET, value = "/user/model")
	public @ResponseBody ResponseEntity<?> getLinhModels() throws Exception {
		
		UserModel user = new UserModel();
		FieldString f = new FieldString();
		f.setMeta(new ModelMeta());
		MetaData m = new MetaData();
		m.setCreateDate(f);
		m.setDocumentTitle(f);
		m.setModifyDate(f);
		user.setMetaData(m);
		user.setUserName(new FieldString());
		user.setUserStatus(new FieldString());

		
		//UserModel user = userService.initModel();
		return ResponseEntity.ok(user);
	}

	// Create a new User
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public @ResponseBody ResponseEntity<?> save(@RequestBody UserModel user) {
		UserModel u = repository.save(user);

		return new ResponseEntity<UserModel>(u, HttpStatus.CREATED);
		
	}

	// find by id
	@RequestMapping(method = RequestMethod.GET, value = "/user/{ID}")
	public @ResponseBody ResponseEntity<?> findbyID(@PathVariable(value = "ID") long id) {
		UserModel p = repository.findOne(id);
		return new ResponseEntity<UserModel>(p, HttpStatus.OK);
	}

	// Find all
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public @ResponseBody ResponseEntity<?> getAll() {
		Page<UserModel> p = (Page<UserModel>) repository.findAll();
		return new ResponseEntity<Page<UserModel>>(p, HttpStatus.OK);
	}

	// Update a user
	@RequestMapping(method = RequestMethod.PUT, value = "/user/{ID}")
	public @ResponseBody ResponseEntity<?> save(@PathVariable(value = "ID") long id, @RequestBody UserModel user) {
		UserModel p = repository.findOne(id);
		p.setUserName(user.getUserName());
		p.setUserStatus(user.getUserStatus());
		p.setMetaData(user.getMetaData());
		UserModel u = repository.save(p);

		return new ResponseEntity<UserModel>(u, HttpStatus.CREATED);
		
		
		//return userService.saveUser(id, user);
	}

	// Delete a user
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{ID}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable(value = "ID") long id) {
		UserModel p = repository.findOne(id);
		// personRepository.delete(p);
		repository.delete(id);

		return new ResponseEntity(HttpStatus.OK);
	}

}