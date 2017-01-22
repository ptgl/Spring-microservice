package spring.demo.linh_model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import spring.demo.linh_model.converter.FieldStringConverter;
import spring.demo.linh_model.FieldString;
//import spring.demo.linh_model.MetaData;


@Entity
@Data
public class UserModel {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	
	private MetaData metaData;
	
	@Convert(converter = FieldStringConverter.class)
	private FieldString userName;
	
	@Convert(converter = FieldStringConverter.class)
	private FieldString userStatus;
	
	
}
