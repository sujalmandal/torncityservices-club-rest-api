package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.JobType;

@Data
public class CreateJobRequestDTO {
    private JobType jobType;
    private int amount=1;
    private Long pay;
    private String listedByPlayerId;
    private String targetPlayerId;
}