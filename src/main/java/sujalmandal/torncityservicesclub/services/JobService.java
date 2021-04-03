package sujalmandal.torncityservicesclub.services;

import java.util.List;

import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobAcceptRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobCancelRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobDetailTemplateDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.dtos.JobFinishRequestDTO;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.JobDetailFilterTemplate;
import sujalmandal.torncityservicesclub.models.JobDetailFormTemplate;

public interface JobService {

    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO jobFilterRequestDTO);

    public Job postJob(CreateJobRequestDTO createJobRequestDTO);

    public Job acceptJob(JobAcceptRequestDTO updateJobRequestDTO);

    public Job finishJob(JobFinishRequestDTO updateJobRequestDTO);

    public Job cancelJob(JobCancelRequestDTO updateJobRequestDTO);

    public JobDetailFormTemplate getJobDetailFormTemplateForTemplateName(String jobDetailFormTemplateName);

    public JobDetailFilterTemplate getJobDetailFilterTemplateForTemplateName(String templateName);

    public List<JobDetailTemplateDTO> getJobDetailTemplateInforamation();

}