package sujalmandal.torncityservicesclub.models;

import lombok.Data;

@Data
public class FormFieldDescriptor {
    private String id;
    private String type;
    private String label;
    private String name;
    private String serviceType;
    private Boolean isHighlighted;
    private String serviceTypeToHighlightOn;
    private String infoText;
}