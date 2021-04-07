package sujalmandal.torncityservicesclub.models;

import java.util.List;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Data
public class FormFieldDescriptor {
	private String id;
	private FormFieldTypeValue type;
	private String label;
	private String name;
	private ServiceTypeValue serviceType;
	private Boolean isHighlighted;
	private String serviceTypeToHighlightOn;
	private String infoText;
	private FieldFormatterValue format;
	private Long minValue;
	private Long maxValue;
	private String defaultValue;
	private List<String> options;
}