package spring.demo.service;

import java.util.List;
import java.util.Optional;

import spring.demo.linh_model.UserModel;

public interface DAO {

	UserModel save(UserModel user);
	Optional<UserModel> findOne(Long id);
	void delete(Long id);
	List<UserModel> findAll();

}
