package spring.demo.linh_model;

import lombok.Data;

@Data
public class FieldString {
	
	private String value;

	private ModelMeta meta;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public ModelMeta getMeta() {
		return meta;
	}

	public void setMeta(ModelMeta meta) {
		this.meta = meta;
	}

	
	
	
	
	
}
