package sujalmandal.torncityservicesclub.dtos.commons;

import java.time.LocalDateTime;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.utils.PojoUtils;


@Data
public class JobDTO {

    private String id;
    private ServiceTypeValue serviceType;
    private int amount;
    private Long pay;
    private LocalDateTime postedDate;

    public static JobDTO fromJob(Job job){
        JobDTO JobDTO = new JobDTO();
        PojoUtils.getModelMapper().map(job, JobDTO);
        return JobDTO;
    }

}