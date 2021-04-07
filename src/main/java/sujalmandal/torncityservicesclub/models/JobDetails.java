package sujalmandal.torncityservicesclub.models;

import java.util.HashMap;
import java.util.Optional;

import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.utils.PojoUtils;
import sujalmandal.torncityservicesclub.utils.TemplateGeneratorUtil;

public interface JobDetails {

    public static JobDetails fromMap(String jobDetailFormeTemplateName, HashMap<String, Object> data) {
	Optional<Class<?>> clazz = TemplateGeneratorUtil.getJobDetailImplClasses().stream().filter(implClass -> {
	    return implClass.getAnnotation(GenerateTemplate.class).value().getFormTemplateName()
		    .equals(jobDetailFormeTemplateName);
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

    default public String getJobDetailFormTemplateName() {
	return this.getClass().getAnnotation(GenerateTemplate.class).value().getFormTemplateName();
    }

    default public String getJobDetailFilterTemplateName() {
	return this.getClass().getAnnotation(GenerateTemplate.class).value().getFilterTemplateName();
    }
}