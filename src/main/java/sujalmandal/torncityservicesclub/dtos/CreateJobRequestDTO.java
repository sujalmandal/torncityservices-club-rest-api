package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceType;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
public class CreateJobRequestDTO {
    private ServiceType serviceType;
    private String apiKey;
    private JobDetails jobDetails;
}