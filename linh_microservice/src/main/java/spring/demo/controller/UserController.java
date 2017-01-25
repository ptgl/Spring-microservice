package spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import spring.demo.linh_model.*;
import spring.demo.repository.UserModelRepository;
import spring.demo.service.UserServiceImpl;

@Controller
public class UserController {

	@Autowired
	private UserModelRepository userRepository;

	@Autowired
	private UserServiceImpl userService;

	// Get empty model
	@RequestMapping(method = RequestMethod.GET, value = "/user/model")
	public @ResponseBody ResponseEntity<?> getLinhModels() throws Exception {
		
//		UserModel user = new UserModel();
//		FieldString f = new FieldString();
//		f.setMeta(new ModelMeta());
//		MetaData m = new MetaData();
//		m.setCreateDate(f);
//		m.setDocumentTitle(f);
//		m.setModifyDate(f);
//		user.setMetaData(m);
//		user.setUserName(new FieldString());
//		user.setUserStatus(new FieldString());

		
		UserModel user = userService.initModel();
		return ResponseEntity.ok(user);
	}

	// Create a new User
	@RequestMapping(method = RequestMethod.POST, value = "/user")
	public @ResponseBody ResponseEntity<?> save(@RequestBody UserModel user) {
		UserModel u = userRepository.save(user);

		return new ResponseEntity<UserModel>(u, HttpStatus.CREATED);
		
	}

	// find by id
	@RequestMapping(method = RequestMethod.GET, value = "/user/{ID}")
	public @ResponseBody ResponseEntity<?> findbyID(@PathVariable(value = "ID") long id) {
		UserModel p = userRepository.findOne(id);
		return new ResponseEntity<UserModel>(p, HttpStatus.OK);
	}

	// Find all
	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public @ResponseBody ResponseEntity<?> getAll() {
		List<UserModel> p = (List<UserModel>) userRepository.findAll();
		return new ResponseEntity<List<UserModel>>(p, HttpStatus.OK);
	}

	// Update a user
	@RequestMapping(method = RequestMethod.PUT, value = "/user/{ID}")
	public @ResponseBody ResponseEntity<?> save(@PathVariable(value = "ID") long id, @RequestBody UserModel user) {
	/*	UserModel p = userRepository.findOne(id);
		p.setUserName(user.getUserName());
		p.setUserStatus(user.getUserStatus());
		p.setMetaData(user.getMetaData());
		UserModel u = userRepository.save(p);

		return new ResponseEntity<UserModel>(u, HttpStatus.CREATED);*/
		
		
		return userService.saveUser(id, user);
	}

	// Delete a user
	@RequestMapping(method = RequestMethod.DELETE, value = "/user/{ID}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable(value = "ID") long id) {
		UserModel p = userRepository.findOne(id);
		// personRepository.delete(p);
		userRepository.delete(id);

		return new ResponseEntity(HttpStatus.OK);
	}

}
