package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceType;
import sujalmandal.torncityservicesclub.models.JobDetails;

@Data
public class CreateJobRequestDTO {
    private ServiceType serviceType;
    private int amount = 1;
    private Long pay;
    private String listedByPlayerId;
    private JobDetails jobDetails;
}