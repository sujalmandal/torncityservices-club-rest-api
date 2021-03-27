package sujalmandal.torncityservicesclub.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobAcceptRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFinishRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobCancelRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterResponseDTO;
import sujalmandal.torncityservicesclub.enums.JobStatus;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.repositories.JobRepository;
import sujalmandal.torncityservicesclub.repositories.PlayerRepository;
import sujalmandal.torncityservicesclub.services.JobService;
import sujalmandal.torncityservicesclub.utils.MongoUtil;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public JobFilterResponseDTO getJobsByFilter(JobFilterRequestDTO filterRequest) {
        try {
            Criteria filterCriteria = MongoUtil.getCriteriaForJobFilterRequest(filterRequest);
            Query filterQuery = new Query(filterCriteria);
            MongoUtil.paginateQuery(filterQuery, filterRequest.getPageNumber(), filterRequest.getPageSize());
            JobFilterResponseDTO response = new JobFilterResponseDTO();
            response.setJobs(mongoTemplate.find(filterQuery, Job.class));
            response.setPageNumber(filterRequest.getPageNumber());
            response.setPageSize(filterRequest.getPageSize());
            return response;
        } catch (Exception e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Job postJob(CreateJobRequestDTO createJobRequestDTO) {
        Job newJob = PojoUtils.getJobFromDTO(createJobRequestDTO);
        Optional<Player> poster = playerRepo.findById(createJobRequestDTO.getListedByPlayerId());
        if (poster.isPresent()) {
            newJob.setListedByPlayerId(poster.get().getInternalId());
            newJob.setPostedDate(LocalDateTime.now());
            return jobRepo.save(newJob);
        } else {
            throw new ServiceException(500, "Player not found in the database!", null);
        }
    }

    @Override
    public Job acceptJob(JobAcceptRequestDTO updateJobRequestDTO) {
        String jobId=updateJobRequestDTO.getId();
        Optional<Job> job = jobRepo.findById(jobId);
        if(job.isPresent()){
            Job updatedJob = job.get();
            updatedJob.setAcceptedByPlayerId(updateJobRequestDTO.getAcceptedByPlayerId());
            updatedJob.setAcceptedDate(LocalDateTime.now());
            updatedJob.setStatus(JobStatus.ACCEPTED);
            return jobRepo.save(updatedJob);
        }
        else{
            throw new ServiceException(500, String.format("job %d not found in the database!", jobId), null);
        }
    }

    @Override
    public Job finishJob(JobFinishRequestDTO updateJobRequestDTO) {
        String jobId=updateJobRequestDTO.getId();
        Optional<Job> job = jobRepo.findById(jobId);
        if(job.isPresent()){
            Job updatedJob = job.get();
            if(updatedJob.getAcceptedByPlayerId()==null || updatedJob.getStatus()==JobStatus.AVAILABLE){
                throw new ServiceException(500, String.format("job %d has not been accepted yet!", jobId), null);
            }
            updatedJob.setFinishedDate(LocalDateTime.now());
            updatedJob.setStatus(JobStatus.FINISHED);
            return jobRepo.save(updatedJob);
        }
        else{
            throw new ServiceException(500, String.format("job %d not found in the database!", jobId), null);
        }
    }

    @Override
    public Job cancelJob(JobCancelRequestDTO updateJobRequestDTO) {
        String jobId=updateJobRequestDTO.getId();
        Optional<Job> job = jobRepo.findById(jobId);
        if(job.isPresent()){
            Job updatedJob = job.get();
            updatedJob.setIsDeleted(Boolean.TRUE);
            return jobRepo.save(updatedJob);
        }
        else{
            throw new ServiceException(500, String.format("job %d not found in the database!", jobId), null);
        }
    }

}