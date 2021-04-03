package sujalmandal.torncityservicesclub.models;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Data
public class FilterFieldDescriptor {
    private ServiceTypeValue serviceType;
    private String fieldType;
    private String fieldName;
    private String fieldLabel;
    private String minFieldName;
    private String maxFieldName;

    public FilterFieldDescriptor(ServiceTypeValue serviceType, String fieldName, String fieldType, String fieldLabel) {
	this.serviceType = serviceType;
	this.fieldName = fieldName;
	this.fieldLabel = fieldLabel;
	this.fieldType = fieldType;
	if (fieldType.equalsIgnoreCase("number")) {
	    this.minFieldName = "min" + fieldName.substring(0, 1).toUpperCase()
		    + fieldName.substring(1, fieldName.length());
	    this.maxFieldName = "max" + fieldName.substring(0, 1).toUpperCase()
		    + fieldName.substring(1, fieldName.length());
	}
    }
}
