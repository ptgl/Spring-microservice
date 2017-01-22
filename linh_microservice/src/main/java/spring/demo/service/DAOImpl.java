package spring.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import spring.demo.linh_model.UserModel;
import spring.demo.repository.UserModelRepository;

public class DAOImpl implements DAO {

	@Autowired
	private UserModelRepository userModelRepository;

	public UserModel save(UserModel user) {

		return userModelRepository.save(user);
	}

	public void delete(Long id) {
		userModelRepository.delete(id);
	
	}
	
	public Optional<UserModel> findOne(Long id) {
		return Optional.ofNullable(userModelRepository.findOne(id));
	
	}
	
	public List<UserModel> findAll() {
		return (List<UserModel>) userModelRepository.findAll();
	
	}

}
