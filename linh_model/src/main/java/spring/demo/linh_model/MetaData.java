package spring.demo.linh_model;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import spring.demo.linh_model.converter.FieldStringConverter;

@Data
@Entity
public class MetaData {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;

	@Convert(converter = FieldStringConverter.class)
	private FieldString documentTitle;
	
	@Convert(converter = FieldStringConverter.class)
	private FieldString createDate;
	
	@Convert(converter = FieldStringConverter.class)
	private FieldString modifyDate;
	
	
}
