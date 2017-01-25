package spring.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.demo.linh_model.FieldString;
import spring.demo.linh_model.MetaData;
import spring.demo.linh_model.ModelMeta;
import spring.demo.linh_model.UserModel;
import spring.demo.repository.UserModelRepository;

@Service
public class UserServiceImpl {
	
	@Autowired
	private UserModelRepository userRepository;
	
	public UserModel initModel(){
		
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
		return user;
		
	}
	
	
	
	
	
	
	public @ResponseBody ResponseEntity<?> saveUser(long id,  UserModel user) 
	{
		UserModel p = userRepository.findOne(id);
		p.setUserName(user.getUserName());
		p.setUserStatus(user.getUserStatus());
		p.setMetaData(user.getMetaData());
		UserModel u = userRepository.save(p);

		return new ResponseEntity<UserModel>(u, HttpStatus.CREATED);
		
		
	}

}
