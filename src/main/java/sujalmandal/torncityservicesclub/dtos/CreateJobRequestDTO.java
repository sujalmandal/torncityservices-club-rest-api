package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.JobType;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Data
public class CreateJobRequestDTO {
    private JobType jobType;
    private int amount;
    private Long pay;
    private String playerId;

    public Job getJob(){
        Job job = new Job();
        PojoUtils.getModelMapper().map(this, job);
        return job;
    }
}