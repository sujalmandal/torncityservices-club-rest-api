package sujalmandal.torncityservicesclub.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobAcceptRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobCloseRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobDeleteRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.repositories.JobRepository;
import sujalmandal.torncityservicesclub.repositories.PlayerRepository;
import sujalmandal.torncityservicesclub.services.JobService;
import sujalmandal.torncityservicesclub.utils.MongoUtil;


@Service
public class JobServiceImpl implements JobService{

    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private JobRepository jobRepo;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Job> getJobsByFilter(JobFilterRequestDTO filterRequest) {
        Criteria filterCriteria = MongoUtil.getCriteriaForJobFilterRequest(filterRequest);
        Query filterQuery=new Query(filterCriteria);
        MongoUtil.paginateQuery(filterQuery, filterRequest.getPageNumber(), filterRequest.getPageSize());
        return mongoTemplate.find(filterQuery, Job.class);
    }

    @Override
    public Job postJob(CreateJobRequestDTO createJobRequestDTO) {
        Job newJob = createJobRequestDTO.getJob();
        Optional<Player> poster = playerRepo.findById(createJobRequestDTO.getPlayerId());
        if(poster.isPresent()){
            newJob.setListedByPlayerId(poster.get().getInternalId());
            newJob.setPostedDate(LocalDateTime.now());
            return jobRepo.save(newJob);
        }
        else{
            throw new ServiceException(500,"Player not found in the database!",null);
        }
    }

    @Override
    public Job acceptJob(JobAcceptRequestDTO updateJobRequestDTO) {
        return null;
    }

    @Override
    public Job closeJob(JobCloseRequestDTO updateJobRequestDTO) {
        return null;
    }

    @Override
    public Job cancelJob(JobDeleteRequestDTO updateJobRequestDTO) {
        return null;
    }

}