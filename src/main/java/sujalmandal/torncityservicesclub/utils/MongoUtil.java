package sujalmandal.torncityservicesclub.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.enums.JobStatus;

public class MongoUtil {

    public static Criteria getCriteriaForJobFilterRequest(JobFilterRequestDTO jobFilterRequestDTO) {
	List<Criteria> criteriaList = new ArrayList<>();
	// default filters
	criteriaList.add(new Criteria("status").is(JobStatus.AVAILABLE));
	criteriaList.add(new Criteria("isDeleted").is(Boolean.FALSE));
	return new Criteria().andOperator(criteriaList.toArray(new Criteria[criteriaList.size()]));
    }

    public static void paginateQuery(Query query, int pageNumber, int pageSize) {
	query.skip(pageNumber * pageSize);
	query.limit(pageSize);
    }
}
