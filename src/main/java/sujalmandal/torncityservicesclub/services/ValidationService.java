package sujalmandal.torncityservicesclub.services;

import java.util.Map;

import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.ServiceDetail;

public interface ValidationService {
    public Map<String, String> validateCreateRequest(ServiceDetail jobDetailImplInstance,
	    ServiceTypeValue serviceTypeValue);
}
