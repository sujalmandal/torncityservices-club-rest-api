package sujalmandal.torncityservicesclub.dtos;

import java.util.HashMap;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;

@Data
public class CreateJobRequestDTO {
    private ServiceTypeValue serviceType;
    private String apiKey;
    private String jobDetailType;
    private HashMap<String, Object> jobDetails;
}