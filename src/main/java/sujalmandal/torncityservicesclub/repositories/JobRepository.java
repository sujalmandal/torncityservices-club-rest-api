package sujalmandal.torncityservicesclub.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sujalmandal.torncityservicesclub.models.Job;

@Repository
public interface JobRepository extends CrudRepository<Job,String>{}