package spring.demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import spring.demo.linh_model.UserModel;
import spring.demo.sample.Person;
//public interface UserRepository extends ElasticsearchRepository<UserModel,String>
public interface UserRepository extends ElasticsearchRepository<UserModel,Long> {

	//@Query("{\"bool\" : {\"must\" : {\"term\" : {\"metaData.documentTitle.value\" : \"?0\"}}}}")
	public List<UserModel> findByMetaDataDocumentTitleValue(String value);
	
}
