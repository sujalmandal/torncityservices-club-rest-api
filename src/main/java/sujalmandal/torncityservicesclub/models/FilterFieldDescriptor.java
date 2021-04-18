package sujalmandal.torncityservicesclub.models;

import java.util.List;

import lombok.Data;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;

@Data
public class FilterFieldDescriptor {

    private FieldTypeValue type;
    private FieldFormatValue format;
    private String name;
    private Long maxValue;
    private Long minValue;
    private String groupName;
    private String defaultValue;
    private List<String> options;
    private String label;

}
