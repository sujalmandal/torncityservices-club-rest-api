package sujalmandal.torncityservicesclub.services;

import java.util.List;

import sujalmandal.torncityservicesclub.dtos.commons.JobDetailTemplateDTO;
import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobUpateRequestDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.JobDetailFilterTemplate;
import sujalmandal.torncityservicesclub.models.JobDetailFormTemplate;

public interface JobService {

    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO jobFilterRequestDTO);

    public Job postJob(CreateJobRequestDTO createJobRequestDTO);

    public Job acceptJob(JobUpateRequestDTO updateJobRequestDTO);

    public Job finishJob(JobUpateRequestDTO updateJobRequestDTO);

    public Job cancelJob(JobUpateRequestDTO updateJobRequestDTO);

    public JobDetailFormTemplate getJobDetailFormTemplateForTemplateName(String jobDetailFormTemplateName);

    public JobDetailFilterTemplate getJobDetailFilterTemplateForTemplateName(String templateName);

    public List<JobDetailTemplateDTO> getJobDetailTemplateInforamation();

}