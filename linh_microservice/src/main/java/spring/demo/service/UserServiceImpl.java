package spring.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import spring.demo.linh_model.UserModel;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	protected DAO DAO;
	
	@Override
	public void delete(Long id) {
		
		DAO.delete(id);	
	}

	@Override
	public ResponseEntity<?> insertUser(UserModel user){
		UserModel individual = DAO.save(user);
		//Resource<UserModel> resource = buildResourceWithRelatedRels(individual);
		return new ResponseEntity<UserModel>(individual, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<?> updateUser(UserModel user, Long id) {
		Optional<UserModel> optional = DAO.findOne(id);
		if(!optional.isPresent()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		UserModel individual = DAO.save(user);
		
		return new ResponseEntity<UserModel>(individual, HttpStatus.OK);
	}

	@Override
	public void read(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserModel initModel() {
		
		return new UserModel();
	}
	

}
