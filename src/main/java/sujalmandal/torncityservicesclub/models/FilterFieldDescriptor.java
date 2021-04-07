package sujalmandal.torncityservicesclub.models;

import java.util.List;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Data
public class FilterFieldDescriptor {
	
	private ServiceTypeValue serviceType;
	private FormFieldTypeValue fieldType;
	private FieldFormatterValue format;
	private String fieldName;
	private String fieldLabel;
	private Long maxValue;
	private Long minValue;
	private String groupName;
	private String defaultValue;
	private List<String> options;
	
	public FilterFieldDescriptor() {
	}
	
}
