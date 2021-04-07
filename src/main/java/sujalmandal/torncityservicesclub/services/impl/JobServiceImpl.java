package sujalmandal.torncityservicesclub.services.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.dtos.commons.JobDetailTemplateDTO;
import sujalmandal.torncityservicesclub.dtos.commons.PlayerDTO;
import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobUpateRequestDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobResponseDTO;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.JobStatus;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.JobDetailFilterTemplate;
import sujalmandal.torncityservicesclub.models.JobDetailFormTemplate;
import sujalmandal.torncityservicesclub.models.JobDetails;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.repositories.JobRepository;
import sujalmandal.torncityservicesclub.repositories.PlayerRepository;
import sujalmandal.torncityservicesclub.services.JobService;
import sujalmandal.torncityservicesclub.services.PlayerService;
import sujalmandal.torncityservicesclub.utils.MongoUtil;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private PlayerService playerService;

    @Override
    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO filterRequest) {
	try {
	    Criteria filterCriteria = MongoUtil.getCriteriaForJobFilterRequest(filterRequest);
	    Query filterQuery = new Query(filterCriteria);
	    long totalResults = mongoTemplate.count(filterQuery, Job.class);
	    MongoUtil.paginateQuery(filterQuery, filterRequest.getPageNumber(), filterRequest.getPageSize());
	    log.info("query : " + filterQuery);
	    JobFilterResponseDTO response = new JobFilterResponseDTO();
	    List<JobResponseDTO> jobDTOs = mongoTemplate.find(filterQuery, Job.class).stream()
		    .map(Job::toJobResponseDTO).collect(Collectors.toList());
	    log.info("found {} results", totalResults);
	    response.setJobs(jobDTOs);
	    response.setPageNumber(filterRequest.getPageNumber());

	    if (totalResults < filterRequest.getPageSize()) {
		response.setPageSize(jobDTOs.size());
	    } else {
		response.setPageSize(filterRequest.getPageSize());
	    }
	    response.setTotalSize(totalResults);
	    if (totalResults > 0) {
		long totalPages = Math.round(totalResults / response.getPageSize() == 0 ? 1
			: Math.round(totalResults / response.getPageSize()));
		response.setTotalPages(totalPages);
	    }
	    return response;
	} catch (Exception e) {
	    throw new ServiceException(e);
	}
    }

    @Override
    public Job postJob(CreateJobRequestDTO request) {
	if (request.getServiceType() == null) {
	    throw new ServiceException("Request must have a valid, non-empty ServiceType !", 400);
	}
	if (request.getServiceType() != ServiceTypeValue.OFFER
		&& request.getServiceType() != ServiceTypeValue.REQUEST) {
	    throw new ServiceException(
		    String.format("Request has invalid ServiceType {%s}", request.getServiceType().toString()), 400);
	}
	Job newJob = PojoUtils.map(request, new Job());
	PlayerDTO listedByPlayer = playerService.authenticateAndReturnPlayer(request.getApiKey());
	Optional<Player> poster = playerRepo.findById(listedByPlayer.getInternalId());
	if (!poster.isPresent()) {
	    throw new ServiceException("Player not found in the database!", 500);
	}
	if (request.getServiceType() == null || request.getServiceType() == ServiceTypeValue.ALL) {
	    throw new ServiceException("Invalid service type selected.", 400);
	}
	newJob.setListedByPlayerId(poster.get().getInternalId());
	newJob.setListedByPlayerName(poster.get().getTornUserName());
	newJob.setPostedDate(LocalDateTime.now());
	JobDetails generatedJobDetail = JobDetails.fromMap(request.getTemplateName(), request.getJobDetails());
	newJob.setFilterTemplateName(generatedJobDetail.getJobDetailFilterTemplateName());
	newJob.setJobDetails(generatedJobDetail);
	return jobRepo.save(newJob);

    }

    @Override
    public Job acceptJob(JobUpateRequestDTO updateJobRequestDTO) {
	String jobId = updateJobRequestDTO.getId();
	Optional<Job> job = jobRepo.findById(jobId);
	if (job.isPresent()) {
	    Job updatedJob = job.get();
	    updatedJob.setAcceptedByPlayerId(updateJobRequestDTO.getAcceptedByPlayerId());
	    updatedJob.setAcceptedDate(LocalDateTime.now());
	    updatedJob.setStatus(JobStatus.ACCEPTED);
	    return jobRepo.save(updatedJob);
	} else {
	    throw new ServiceException(500, String.format("job %d not found in the database!", jobId), null);
	}
    }

    @Override
    public Job finishJob(JobUpateRequestDTO updateJobRequestDTO) {
	String jobId = updateJobRequestDTO.getId();
	Optional<Job> job = jobRepo.findById(jobId);
	if (job.isPresent()) {
	    Job updatedJob = job.get();
	    if (updatedJob.getAcceptedByPlayerId() == null || updatedJob.getStatus() == JobStatus.AVAILABLE) {
		throw new ServiceException(500, String.format("job %d has not been accepted yet!", jobId), null);
	    }
	    updatedJob.setFinishedDate(LocalDateTime.now());
	    updatedJob.setStatus(JobStatus.FINISHED);
	    return jobRepo.save(updatedJob);
	} else {
	    throw new ServiceException(500, String.format("job %d not found in the database!", jobId), null);
	}
    }

    @Override
    public Job cancelJob(JobUpateRequestDTO updateJobRequestDTO) {
	String jobId = updateJobRequestDTO.getId();
	Optional<Job> job = jobRepo.findById(jobId);
	if (job.isPresent()) {
	    Job updatedJob = job.get();
	    updatedJob.setIsDeleted(Boolean.TRUE);
	    return jobRepo.save(updatedJob);
	} else {
	    throw new ServiceException(500, String.format("job %d not found in the database!", jobId), null);
	}
    }

    @Override
    public JobDetailFormTemplate getJobDetailFormTemplateForTemplateName(String templateName) {
	if (StringUtils.isNotEmpty(templateName)) {
	    List<JobDetailFormTemplate> templates = mongoTemplate
		    .find(new Query(Criteria.where("formTemplateName").is(templateName)), JobDetailFormTemplate.class);
	    if (CollectionUtils.isEmpty(templates)) {
		throw new ServiceException(
			String.format("No template found for the passed template name : {%s} !", templateName), 400);
	    }
	    return templates.get(0);
	} else {
	    throw new ServiceException("Job detail template name cannot be empty!", 400);
	}
    }

    @Override
    public List<JobDetailTemplateDTO> getJobDetailTemplateInformation() {
	return Arrays.asList(JobDetailTemplateValue.values()).stream().map(JobDetailTemplateDTO::new)
		.collect(Collectors.toList());
    }

    @Override
    public JobDetailFilterTemplate getJobDetailFilterTemplateForTemplateName(String templateName) {
	if (StringUtils.isNotEmpty(templateName)) {
	    List<JobDetailFilterTemplate> templates = mongoTemplate.find(
		    new Query(Criteria.where("filterTemplateName").is(templateName)), JobDetailFilterTemplate.class);
	    if (CollectionUtils.isEmpty(templates)) {
		throw new ServiceException(
			String.format("No template found for the passed template name : {%s} !", templateName), 400);
	    }
	    return templates.get(0);
	} else {
	    throw new ServiceException("Job detail template name  cannot be empty!", 400);
	}
    }

}