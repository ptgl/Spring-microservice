package spring.demo.linh_model.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import spring.demo.linh_model.FieldString;
import spring.demo.linh_model.ModelMeta;

@Converter
public class FieldStringConverter implements AttributeConverter<FieldString,String>{

	//@Override
	public String convertToDatabaseColumn(FieldString attribute) {
		return attribute.getValue();
	}

	//@Override
	public FieldString convertToEntityAttribute(String dbData) {
		FieldString fieldString = new FieldString();
		fieldString.setValue(dbData);
		fieldString.setMeta(new ModelMeta());
		return fieldString;
	}

}
