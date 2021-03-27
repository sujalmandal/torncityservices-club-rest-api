package sujalmandal.torncityservicesclub.utils;

import org.modelmapper.ModelMapper;

import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.enums.JobType;
import sujalmandal.torncityservicesclub.models.Job;

public class PojoUtils {
    private static ModelMapper mapper = new ModelMapper();

    public static ModelMapper getModelMapper(){
        return mapper;
    }

    public static Job getJobFromDTO(CreateJobRequestDTO request){
        Job job = new Job();
        PojoUtils.getModelMapper().map(request, job);
        if(!request.getJobType().toString().contains("FIGHT") || request.getJobType()!=JobType.BOUNTY){
            request.setAmount(1);
        }
        return job;
    }
}
