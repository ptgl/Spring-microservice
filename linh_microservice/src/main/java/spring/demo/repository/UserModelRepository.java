package spring.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import spring.demo.linh_model.UserModel;


@RepositoryRestResource(collectionResourceRel = "users", path = "users")
public interface UserModelRepository extends PagingAndSortingRepository<UserModel, Long>{
}
