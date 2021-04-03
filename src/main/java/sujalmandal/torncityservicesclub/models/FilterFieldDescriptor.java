package sujalmandal.torncityservicesclub.models;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Data
public class FilterFieldDescriptor {

    private ServiceTypeValue serviceType;
    private String fieldType;
    private String fieldName;
    private String fieldLabel;
    private String limit;
    private String format;

    public FilterFieldDescriptor() {
    }

    public FilterFieldDescriptor(ServiceTypeValue serviceType, String fieldType, String fieldName, String fieldLabel) {
	this.serviceType = serviceType;
	this.fieldType = fieldType;
	this.fieldName = fieldName;
	this.fieldLabel = fieldLabel;
    }

}
