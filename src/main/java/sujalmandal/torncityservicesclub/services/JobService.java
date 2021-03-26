package sujalmandal.torncityservicesclub.services;

import java.util.List;

import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.UpdateJobRequestDTO;
import sujalmandal.torncityservicesclub.models.Job;

public interface JobService {
    
    public List<Job> getJobsByFilter(JobFilterRequestDTO jobFilterRequestDTO);
    public Job postJob(CreateJobRequestDTO createJobRequestDTO);
    public Job acceptJob(UpdateJobRequestDTO updateJobRequestDTO);
    public Job closeJob(UpdateJobRequestDTO updateJobRequestDTO);
    public Job cancelJob(UpdateJobRequestDTO updateJobRequestDTO);
    
}