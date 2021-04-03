package sujalmandal.torncityservicesclub.models;

import java.util.HashMap;
import java.util.Optional;

import sujalmandal.torncityservicesclub.annotations.JobDetailTemplateKey;
import sujalmandal.torncityservicesclub.utils.AppUtils;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

public interface JobDetails {

    public static JobDetails fromMap(String jobDetailKey, HashMap<String, Object> data) {
	Optional<Class<?>> clazz = AppUtils.getJobDetailImplClasses().stream().filter(implClass -> {
	    return implClass.getAnnotation(JobDetailTemplateKey.class).value().getKey().equals(jobDetailKey);
	}).findFirst();
	if (clazz.isPresent()) {
	    return (JobDetails) PojoUtils.getObjectMapper().convertValue(data, clazz.get());
	} else {
	    return null;
	}
    }

    @SuppressWarnings("unchecked")
    default public HashMap<String, Object> toMap() {
	return PojoUtils.getObjectMapper().convertValue(this, HashMap.class);
    }

    default public String getJobDetailType() {
	return this.getClass().getAnnotation(JobDetailTemplateKey.class).value().getKey();
    }
}