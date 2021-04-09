package sujalmandal.torncityservicesclub.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import sujalmandal.torncityservicesclub.models.MongoSequence;

@Repository
public interface MongoSequenceRepo extends CrudRepository<MongoSequence, String> {
}