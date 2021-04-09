package sujalmandal.torncityservicesclub.services.impl;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.dtos.commons.JobDetailTemplateDTO;
import sujalmandal.torncityservicesclub.dtos.commons.ValidationMessage;
import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobUpateRequestDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobResponseDTO;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
import sujalmandal.torncityservicesclub.enums.JobFilterCriteriaField;
import sujalmandal.torncityservicesclub.enums.JobStatus;
import sujalmandal.torncityservicesclub.enums.MongoCollections;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.JobDetailFilterTemplate;
import sujalmandal.torncityservicesclub.models.JobDetailFormTemplate;
import sujalmandal.torncityservicesclub.models.JobDetails;
import sujalmandal.torncityservicesclub.models.MongoSequence;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.repositories.JobRepository;
import sujalmandal.torncityservicesclub.repositories.MongoSequenceRepo;
import sujalmandal.torncityservicesclub.services.JobService;
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

    @Override
    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO filterRequest) {
	try {
	    Criteria filterCriteria = MongoUtil.getCriteriaForJobFilterRequest(filterRequest);
	    Query filterQuery = new Query(filterCriteria);
	    long totalResults = mongoTemplate.count(filterQuery, Job.class);
	    Pageable pageable = PageRequest.of(filterRequest.getPageNumber() - 1, filterRequest.getPageSize(),
		    Sort.by(Order.desc(JobFilterCriteriaField.POSTED_DATE.toString())));
	    filterQuery.with(pageable);
	    log.info("query : " + filterQuery);
	    List<Job> results = mongoTemplate.find(filterQuery, Job.class);
	    JobFilterResponseDTO response = new JobFilterResponseDTO();
	    List<JobResponseDTO> jobDTOs = results.stream().map(Job::toJobResponseDTO).collect(Collectors.toList());
	    log.info("found {} results", totalResults);
	    response.setJobs(jobDTOs);
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
	JobDetails jobDetailImplInstance = JobDetails.fromMap(request.getTemplateName(), request.getJobDetails());
	Job newJob = PojoUtils.map(request, new Job());
	List<ValidationMessage> errors = validateCreateRequest(jobDetailImplInstance);
	if (errors.isEmpty()) {
	    Player poster = request.getPlayer();
	    if (poster == null) {
		throw new ServiceException("Player not found in the security context!", 500);
	    }
	    if (request.getServiceType() == null || request.getServiceType() == ServiceTypeValue.ALL) {
		throw new ServiceException("Invalid service type selected.", 400);
	    }
	    newJob.setListedByPlayerId(poster.getInternalId());
	    newJob.setListedByPlayerName(poster.getTornUserName());
	    newJob.setPostedDate(LocalDateTime.now());
	    newJob.setFilterTemplateName(jobDetailImplInstance.getJobDetailFilterTemplateName());
	    newJob.setJobDetails(jobDetailImplInstance);
	    return saveJobWithSeqId(newJob);
	} else {
	    throw new ServiceException("There were some issues with your input. ", errors, 400);
	}
    }

    /*
     * use serializable isolation level to prevent multiple threads from writing the
     * same sequence id
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
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

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void initSequenceIdsIfNotPresent() {
	MongoSequence collectionSequence = mongoTemplate.findById(MongoCollections.MONGO_SEQUENCE.toString(),
		MongoSequence.class);
	if (collectionSequence == null) {
	    mongoTemplate.save(new MongoSequence(MongoCollections.JOB.toString(), 0L));
	    log.info("initialized mongo sequence id for {} collection", MongoCollections.JOB.toString());
	} else {
	    log.info("mongo sequence already initialized.");
	}
    }

    private List<ValidationMessage> validateCreateRequest(JobDetails jobDetailImplInstance) {
	List<ValidationMessage> errorMessages = new ArrayList<ValidationMessage>();
	JobDetails.getFieldDetails(jobDetailImplInstance.getJobDetailFormTemplateName())
		.forEach((fieldName, formField) -> {
		    try {
			Field currentField = jobDetailImplInstance.getClass().getDeclaredField(fieldName);
			currentField.setAccessible(true);

			String javaType = currentField.getType().getName();
			FormFieldTypeValue fieldType = formField.type();
			Object value = currentField.get(jobDetailImplInstance);

			log.info("validating '{}' of type '{}' with value '{}'", fieldName, javaType, value);

			validateEmptyFields(errorMessages, fieldName, formField, value);

			if (fieldType == FormFieldTypeValue.NUMBER) {
			    if (javaType.contains(Long.class.getSimpleName())) {
				Long valueAsLong = (Long) value;
				if (valueAsLong != null) {
				    long minValue = formField.minValue();
				    long maxValue = formField.maxValue();
				    validateMinMaxValues(errorMessages, fieldName, valueAsLong, minValue, maxValue);
				}
			    }
			    if (javaType.contains(Integer.class.getSimpleName())) {
				Integer valueAsInt = (Integer) value;
				if (valueAsInt != null) {
				    long minValue = formField.minValue();
				    long maxValue = formField.maxValue();
				    validateMinMaxValues(errorMessages, fieldName, valueAsInt, minValue, maxValue);
				}
			    }
			}
			if (fieldType == FormFieldTypeValue.SELECT) {
			    List<String> allowableValues = Arrays.asList(formField.options());
			    validateOptions(errorMessages, fieldName, value, allowableValues);
			}

		    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
			    | IllegalAccessException e) {
			throw new ServiceException(e.getMessage(), 500);
		    }
		});
	return errorMessages;
    }

    private void validateOptions(List<ValidationMessage> errorMessages, String fieldName, Object value,
	    List<String> allowableValues) {
	if (!allowableValues.contains(value)) {
	    errorMessages.add(new ValidationMessage(fieldName,
		    String.format("'%s' is not a valid option for this field", value)));
	}
    }

    private void validateEmptyFields(List<ValidationMessage> errorMessages, String fieldName, FormField formField,
	    Object value) {
	if (!formField.optional() && value == null) {
	    errorMessages.add(new ValidationMessage(fieldName, "field cannot be empty"));
	}
    }

    private void validateMinMaxValues(List<ValidationMessage> errorMessages, String fieldName, Integer valueAsLong,
	    long minValue, long maxValue) {
	if (valueAsLong < minValue) {
	    errorMessages.add(new ValidationMessage(fieldName,
		    String.format("field has a value smaller than minimum allowed value %s", minValue)));
	}
	if (valueAsLong > maxValue) {
	    errorMessages.add(new ValidationMessage(fieldName,
		    String.format("field has a value larger than maximum allowed value of %s", maxValue)));
	}
    }

    private void validateMinMaxValues(List<ValidationMessage> errorMessages, String fieldName, Long valueAsLong,
	    long minValue, long maxValue) {
	validateMinMaxValues(errorMessages, fieldName, valueAsLong.intValue(), minValue, maxValue);
    }

}