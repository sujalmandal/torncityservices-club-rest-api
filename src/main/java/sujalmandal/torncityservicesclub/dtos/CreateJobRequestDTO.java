package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.JobType;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Data
public class CreateJobRequestDTO {
    private JobType jobType;
    private int amount=1;
    private Long pay;
    private String playerId;

    public Job getJob(){
        Job job = new Job();
        PojoUtils.getModelMapper().map(this, job);
        if(!this.jobType.toString().contains("FIGHT") || this.jobType!=JobType.BOUNTY){
            this.amount=1;
        }
        return job;
    }
}