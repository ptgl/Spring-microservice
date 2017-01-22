package spring.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import spring.demo.linh_model.UserModel;
import spring.demo.service.UserService;

public class UserController {

	@Autowired
	protected UserService userService;
	
	@HystrixCommand
	@RequestMapping(method = RequestMethod.GET, value = "/users/model")
	public @ResponseBody ResponseEntity<?> getModels() throws Exception
	{
		UserModel user = userService.initModel();
		return ResponseEntity.ok(user);
	}
	
	
	@HystrixCommand
	@RequestMapping(method = RequestMethod.POST, value = "/users")
	public @ResponseBody ResponseEntity<?> save(@RequestBody UserModel user)
	{
		
		// Save User
		return userService.insertUser(user);
	}
	
	
	@HystrixCommand
	@RequestMapping(method = RequestMethod.PUT, value = "/users/{ID}")
	public @ResponseBody ResponseEntity<?> update(@RequestBody UserModel user, @PathVariable(value="ID") Long id) throws Exception
	{
		//contactIndividual.getMetaData().setModifyDate(helper.buildFieldDate(new Date(), new ModelMeta()));
		return userService.updateUser(user, id);
	}
	
	@HystrixCommand
	@RequestMapping(method = RequestMethod.DELETE, value = "/users/{ID}")
	public @ResponseBody ResponseEntity<?> delete(@PathVariable(value="ID") long id)
	{
		userService.delete(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	
}
