package spring.demo.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import spring.demo.linh_model.UserModel;

public interface UserRepository extends ElasticsearchRepository<UserModel,Long> {

	
	
}
