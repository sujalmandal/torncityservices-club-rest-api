package sujalmandal.torncityservicesclub.dtos;

import java.util.HashMap;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceType;

@Data
public class CreateJobRequestDTO {
    private ServiceType serviceType;
    private String apiKey;
    private String jobDetailType;
    private HashMap<String, Object> jobDetails;
}