package sujalmandal.torncityservicesclub.dtos.commons;

import lombok.Data;

@Data
public class FilterFieldDTO {

    private String type;
    private String name;
    private String minValue;
    private String maxValue;
    private String value;

}