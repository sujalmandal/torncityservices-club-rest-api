package sujalmandal.torncityservicesclub.services;

import java.util.Map;

import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.JobDetails;

public interface ValidationService {
    public Map<String, String> validateCreateRequest(JobDetails jobDetailImplInstance,
	    ServiceTypeValue serviceTypeValue);
}
