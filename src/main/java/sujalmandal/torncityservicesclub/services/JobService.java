package sujalmandal.torncityservicesclub.services;

import java.util.List;

import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobAcceptRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobCloseRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobDeleteRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.models.Job;

public interface JobService {
    
    public List<Job> getJobsByFilter(JobFilterRequestDTO jobFilterRequestDTO);
    public Job postJob(CreateJobRequestDTO createJobRequestDTO);
    public Job acceptJob(JobAcceptRequestDTO updateJobRequestDTO);
    public Job closeJob(JobCloseRequestDTO updateJobRequestDTO);
    public Job cancelJob(JobDeleteRequestDTO updateJobRequestDTO);
    
}