package sujalmandal.torncityservicesclub.services;

import java.util.List;

import sujalmandal.torncityservicesclub.dtos.commons.JobDetailTemplateDTO;
import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobUpateRequestDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.FilterTemplate;
import sujalmandal.torncityservicesclub.models.FormTemplate;

public interface JobService {

    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO jobFilterRequestDTO);

    public Job postJob(CreateJobRequestDTO createJobRequestDTO);

    public Job acceptJob(JobUpateRequestDTO updateJobRequestDTO);

    public Job finishJob(JobUpateRequestDTO updateJobRequestDTO);

    public Job cancelJob(JobUpateRequestDTO updateJobRequestDTO);

    public FormTemplate getJobDetailFormTemplateForTemplateName(String jobDetailFormTemplateName);

    public FilterTemplate getJobDetailFilterTemplateForTemplateName(String templateName);

    public List<JobDetailTemplateDTO> getJobDetailTemplateInformation();

    public void initSequenceIdsIfNotPresent();

}