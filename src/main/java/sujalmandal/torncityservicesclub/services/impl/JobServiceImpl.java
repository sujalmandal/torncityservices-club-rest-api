package sujalmandal.torncityservicesclub.services.impl;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.constants.AppConstants;
import sujalmandal.torncityservicesclub.constants.JobFieldNames;
import sujalmandal.torncityservicesclub.constants.JobStatus;
import sujalmandal.torncityservicesclub.constants.MongoCollections;
import sujalmandal.torncityservicesclub.constants.PayFieldTypeValue;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.dtos.commons.JobDetailTemplateDTO;
import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobUpateRequestDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobDetailFieldResponseDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobDetailResponseDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobResponseDTO;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.FilterTemplate;
import sujalmandal.torncityservicesclub.models.FormTemplate;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.JobDetails;
import sujalmandal.torncityservicesclub.models.MongoSequence;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.repositories.JobRepository;
import sujalmandal.torncityservicesclub.repositories.MongoSequenceRepo;
import sujalmandal.torncityservicesclub.services.JobService;
import sujalmandal.torncityservicesclub.services.ValidationService;
import sujalmandal.torncityservicesclub.utils.MongoUtil;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Service
@Slf4j
public class JobServiceImpl implements JobService {

    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private MongoSequenceRepo mongoSeqRepo;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Autowired
    private ValidationService validationService;

    @Override
    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO filterRequest) {
	try {
	    Criteria filterCriteria = MongoUtil.getFilterCriteria(filterRequest);
	    Query filterQuery = new Query(filterCriteria);
	    if (!filterRequest.isFetchDetails()) {
		filterQuery.fields().exclude(JobFieldNames.JOB_DETAILS.toString());
	    }
	    long totalResults = mongoTemplate.count(filterQuery, Job.class);
	    Pageable pageable = PageRequest.of(filterRequest.getPageNumber() - 1, filterRequest.getPageSize(),
		    Sort.by(Order.desc(JobFieldNames.POSTED_DATE.toString())));
	    filterQuery.with(pageable);
	    log.info("query : " + filterQuery);
	    List<Job> results = mongoTemplate.find(filterQuery, Job.class);
	    JobFilterResponseDTO response = new JobFilterResponseDTO();
	    List<JobResponseDTO> jobDTOs = results.stream().map(Job::toJobResponseDTO).collect(Collectors.toList());
	    log.info("found {} results", totalResults);

	    if (filterRequest.isFetchDetails()) {
		extractJobDetails(results, jobDTOs);
	    }

	    response.setJobs(jobDTOs);
	    setPaginationDetails(filterRequest, totalResults, response, jobDTOs);
	    return response;
	} catch (Exception e) {
	    throw new ServiceException(e);
	}
    }

    private void extractJobDetails(List<Job> results, List<JobResponseDTO> jobDTOs) {
	for (int i = 0; i < results.size(); i++) {
	    Job job = results.get(i);
	    String playerName = job.getListedByPlayerName();
	    JobDetails jdInstance = job.getJobDetails();
	    JobDetailResponseDTO jobDetailResponseDTO = new JobDetailResponseDTO();
	    JobDetails.getFieldDetails(jdInstance.getJobDetailFormTemplateName()).forEach((fieldName, formField) -> {
		try {
		    JobDetailFieldResponseDTO field = new JobDetailFieldResponseDTO();
		    Field currentField = jdInstance.getClass().getDeclaredField(fieldName);
		    currentField.setAccessible(true);
		    field.setValue(currentField.get(jdInstance));
		    field.setFormat(formField.formatter().toString());
		    field.setLabel(formField.labelCommon());
		    field.setType(formField.type().toString());
		    if (StringUtils.isNotEmpty(formField.viewLabelOffer())) {
			field.setLabelOffer(
				formField.viewLabelOffer().replaceAll(AppConstants.PLAYER_PLACEHOLDER, playerName));
		    }
		    if (StringUtils.isNotEmpty(formField.viewLabelRequest())) {
			field.setLabelRequest(
				formField.viewLabelRequest().replaceAll(AppConstants.PLAYER_PLACEHOLDER, playerName));
		    }
		    jobDetailResponseDTO.getFields().add(field);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException
			| IllegalAccessException e) {
		    log.error(e.getMessage(), e);
		    throw new ServiceException("Unable to parse job details!", 500);
		}
	    });
	    jobDTOs.get(i).setDetails(jobDetailResponseDTO);
	}
    }

    private void setPaginationDetails(JobFilterRequestDTO filterRequest, long totalResults,
	    JobFilterResponseDTO response, List<JobResponseDTO> jobDTOs) {
	response.setTotalSize(totalResults);
	response.setPageNumber(filterRequest.getPageNumber());
	if (totalResults == 0) {
	    response.setTotalPages(0);
	    response.setPageSize(0);
	} else {
	    long fullyFilledPages = totalResults / filterRequest.getPageSize();
	    long partiallyFilledPages = (totalResults % filterRequest.getPageSize() == 0) ? 0 : 1;
	    response.setPageSize(jobDTOs.size());
	    response.setTotalPages(fullyFilledPages + partiallyFilledPages);
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
	JobDetails jobDetailImplInstance = JobDetails.fromMap(request.getTemplateName(), request.getJobDetails());
	Job job = PojoUtils.map(request, new Job());
	Map<String, String> errors = validationService.validateCreateRequest(jobDetailImplInstance,
		request.getServiceType());
	if (errors.isEmpty()) {
	    Player poster = request.getPlayer();
	    if (poster == null) {
		throw new ServiceException("Player not found in the security context!", 500);
	    }
	    if (request.getServiceType() == null || request.getServiceType() == ServiceTypeValue.ALL) {
		throw new ServiceException("Invalid service type selected.", 400);
	    }
	    if (job.getServiceType() == ServiceTypeValue.OFFER) {
		job.setTemplateLabel(jobDetailImplInstance.getJobDetailFormTemplateLabelForOffer());
	    }
	    if (job.getServiceType() == ServiceTypeValue.REQUEST) {
		job.setTemplateLabel(jobDetailImplInstance.getJobDetailFormTemplateLabelForRequest());
	    }
	    job.setListedByPlayerId(poster.getInternalId());
	    job.setListedByPlayerName(poster.getTornUserName());
	    job.setPostedDate(LocalDateTime.now());
	    job.setFilterTemplateName(jobDetailImplInstance.getJobDetailFilterTemplateName());
	    job.setJobDetails(jobDetailImplInstance);
	    copyPayDetails(job, jobDetailImplInstance);
	    return saveJobWithSeqId(job);
	} else {
	    throw new ServiceException("There were some issues with your input. ", errors, 400);
	}
    }

    private void copyPayDetails(Job job, JobDetails jobDetailInstance) {
	JobDetails.getFieldDetails(jobDetailInstance.getJobDetailFormTemplateName())
		.forEach((fieldName, templateField) -> {
		    try {
			Field currentField = jobDetailInstance.getClass().getDeclaredField(fieldName);
			currentField.setAccessible(true);
			if (templateField.payFieldType() == PayFieldTypeValue.TOTAL) {
			    job.setTotalPay((Long) currentField.get(jobDetailInstance));
			    job.setPayOnServiceType(templateField.payOnServiceType());
			}
			if (templateField.payFieldType() == PayFieldTypeValue.PER_ACTION) {
			    job.setPayPerAction((Long) currentField.get(jobDetailInstance));
			    job.setPayOnServiceType(templateField.payOnServiceType());
			}
		    } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			throw new ServiceException(e.getMessage(), 500);
		    }
		});
    }

    /*
     * use serializable isolation level to prevent multiple threads from writing the
     * same sequence id
     */
    @Transactional(
	    isolation = Isolation.SERIALIZABLE
    )
    private Job saveJobWithSeqId(Job job) {
	Optional<MongoSequence> collectionSequence = mongoSeqRepo.findById(MongoCollections.JOB.toString());
	if (collectionSequence.isPresent()) {
	    MongoSequence seq = collectionSequence.get();
	    seq.setSequence(seq.getSequence() + 1);
	    mongoSeqRepo.save(seq);
	    job.setSeqId(seq.getSequence());
	    return jobRepo.save(job);
	}
	throw new ServiceException("Sequence not initialised for job collection!", 500);
    }

    @Override
    public Job acceptJob(JobUpateRequestDTO updateJobRequestDTO) {
	String jobId = updateJobRequestDTO.getId();
	Optional<Job> job = jobRepo.findById(jobId);
	if (job.isPresent()) {
	    Job updatedJob = job.get();
	    // updatedJob.setAcceptedByPlayerId(updateJobRequestDTO.getPlayer().getTornUserId());
	    updatedJob.setAcceptedDate(LocalDateTime.now());
	    updatedJob.setStatus(JobStatus.ACCEPTED);
	    return jobRepo.save(updatedJob);
	} else {
	    throw new ServiceException(String.format("job %d not found in the database!", jobId), 500);
	}
    }

    @Override
    public Job finishJob(JobUpateRequestDTO updateJobRequestDTO) {
	String jobId = updateJobRequestDTO.getId();
	Optional<Job> job = jobRepo.findById(jobId);
	if (job.isPresent()) {
	    Job updatedJob = job.get();
	    if (updatedJob.getAcceptedByPlayerId() == null || updatedJob.getStatus() == JobStatus.AVAILABLE) {
		throw new ServiceException(String.format("job %d has not been accepted yet!", jobId), 500);
	    }
	    updatedJob.setFinishedDate(LocalDateTime.now());
	    updatedJob.setStatus(JobStatus.FINISHED);
	    return jobRepo.save(updatedJob);
	} else {
	    throw new ServiceException(String.format("job %d not found in the database!", jobId), 500);
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
	    throw new ServiceException(String.format("job %d not found in the database!", jobId), 500);
	}
    }

    @Override
    public FormTemplate getJobDetailFormTemplateForTemplateName(String templateName) {
	if (StringUtils.isNotEmpty(templateName)) {
	    List<FormTemplate> templates = mongoTemplate
		    .find(new Query(Criteria.where("formTemplateName").is(templateName)), FormTemplate.class);
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
	return Arrays.asList(TemplateValue.values()).stream().map(JobDetailTemplateDTO::new)
		.collect(Collectors.toList());
    }

    @Override
    public FilterTemplate getJobDetailFilterTemplateForTemplateName(String templateName) {
	if (StringUtils.isNotEmpty(templateName)) {
	    List<FilterTemplate> templates = mongoTemplate
		    .find(new Query(Criteria.where("filterTemplateName").is(templateName)), FilterTemplate.class);
	    if (CollectionUtils.isEmpty(templates)) {
		throw new ServiceException(
			String.format("No template found for the passed template name : {%s} !", templateName), 400);
	    }
	    return templates.get(0);
	} else {
	    throw new ServiceException("Job detail template name  cannot be empty!", 400);
	}
    }

    @Transactional(
	    isolation = Isolation.SERIALIZABLE
    )
    public void initSequenceIdsIfNotPresent() {
	MongoSequence collectionSequence = mongoTemplate.findById(MongoCollections.JOB.toString(), MongoSequence.class);
	if (collectionSequence == null) {
	    mongoTemplate.save(new MongoSequence(MongoCollections.JOB.toString(), 0L));
	    log.info("initialized mongo sequence id for {} collection", MongoCollections.JOB.toString());
	} else {
	    log.info("mongo sequence already initialized.");
	}
    }

}