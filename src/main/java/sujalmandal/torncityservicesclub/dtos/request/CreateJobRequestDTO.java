package sujalmandal.torncityservicesclub.dtos.request;

import java.util.HashMap;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class CreateJobRequestDTO extends RequestDTO {
    private ServiceTypeValue serviceType;
    private String jobDetailType;
    private HashMap<String, Object> jobDetails;
}