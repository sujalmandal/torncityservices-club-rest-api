package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobAcceptRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFinishRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobDeleteRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.models.Job;

public interface JobService {
    
    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO jobFilterRequestDTO);
    public Job postJob(CreateJobRequestDTO createJobRequestDTO);
    public Job acceptJob(JobAcceptRequestDTO updateJobRequestDTO);
    public Job finishJob(JobFinishRequestDTO updateJobRequestDTO);
    public Job cancelJob(JobDeleteRequestDTO updateJobRequestDTO);
    
}