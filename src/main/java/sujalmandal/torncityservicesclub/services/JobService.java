package sujalmandal.torncityservicesclub.services;

import java.util.List;

import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobUpateRequestDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.Template;

public interface JobService {

    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO jobFilterRequestDTO);

    public Job postJob(CreateJobRequestDTO createJobRequestDTO);

    public Job acceptJob(JobUpateRequestDTO updateJobRequestDTO);

    public Job finishJob(JobUpateRequestDTO updateJobRequestDTO);

    public Job cancelJob(JobUpateRequestDTO updateJobRequestDTO);

    public Template getTemplateByTemplateName(String templateName);

    public List<String> getAvailableTemplateNames();

    public void initSequenceIdsIfNotPresent();

}