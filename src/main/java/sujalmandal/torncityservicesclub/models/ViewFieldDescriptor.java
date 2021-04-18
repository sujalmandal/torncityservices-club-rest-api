package sujalmandal.torncityservicesclub.models;

import lombok.Data;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;

@Data
public class ViewFieldDescriptor {
    private FieldTypeValue type;
    private String label;
    private String infoText;
    private FieldFormatValue format;
}