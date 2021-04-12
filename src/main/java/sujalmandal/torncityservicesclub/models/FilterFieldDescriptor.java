package sujalmandal.torncityservicesclub.models;

import java.util.List;

import lombok.Data;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;

@Data
public class FilterFieldDescriptor {

    private ServiceTypeValue serviceType;
    private FieldTypeValue fieldType;
    private FieldFormatValue format;
    private String fieldName;
    private String fieldLabel;
    private Long maxValue;
    private Long minValue;
    private String groupName;
    private String defaultValue;
    private List<String> options;
    private String labelRequest;
    private String labelOffer;

    public FilterFieldDescriptor() {
    }

}
