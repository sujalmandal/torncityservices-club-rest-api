package sujalmandal.torncityservicesclub.models;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Data
public class FormFieldDescriptor {
    private String id;
    private String type;
    private String label;
    private String name;
    private ServiceTypeValue serviceType;
    private Boolean isHighlighted;
    private String serviceTypeToHighlightOn;
    private String infoText;
    private String format;

}