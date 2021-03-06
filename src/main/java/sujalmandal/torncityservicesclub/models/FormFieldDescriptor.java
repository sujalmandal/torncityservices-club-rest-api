package sujalmandal.torncityservicesclub.models;

import java.util.List;

import lombok.Data;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;

@Data
public class FormFieldDescriptor {
    private String id;
    private FieldTypeValue type;
    private String label;
    private String name;
    private String infoText;
    private FieldFormatValue format;
    private Long minValue;
    private Long maxValue;
    private String defaultValue;
    private List<String> options;
}