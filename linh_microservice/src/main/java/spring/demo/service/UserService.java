package spring.demo.service;

import org.springframework.http.ResponseEntity;

import spring.demo.linh_model.UserModel;

public interface UserService {

	void delete(Long id);
	ResponseEntity<?> insertUser(UserModel user);
	
	void read(long id);
	ResponseEntity<?> updateUser(UserModel user, Long id);
	UserModel initModel();

}
