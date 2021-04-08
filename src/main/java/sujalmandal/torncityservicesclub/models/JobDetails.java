package sujalmandal.torncityservicesclub.models;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.utils.PojoUtils;
import sujalmandal.torncityservicesclub.utils.TemplateGeneratorUtil;

public interface JobDetails {

    public static JobDetails fromMap(String jobDetailFormTemplateName, HashMap<String, Object> data) {
	Optional<Class<?>> clazz = getImplementation(jobDetailFormTemplateName);
	if (clazz.isPresent()) {
	    return (JobDetails) PojoUtils.getObjectMapper().convertValue(data, clazz.get());
	} else {
	    return null;
	}
    }

    private static Optional<Class<?>> getImplementation(String jobDetailFormTemplateName) {
	Optional<Class<?>> clazz = TemplateGeneratorUtil.getJobDetailImplClasses().stream().filter(implClass -> {
	    return implClass.getAnnotation(GenerateTemplate.class).value().getFormTemplateName()
		    .equals(jobDetailFormTemplateName);
	}).findFirst();
	return clazz;
    }

    public static Map<String, FormField> getFieldDetails(String jobDetailFormTemplateName) {
	Map<String, FormField> formFields = new HashMap<String, FormField>();
	for (Field f : getImplementation(jobDetailFormTemplateName).get().getDeclaredFields()) {
	    formFields.put(f.getName(), f.getAnnotation(FormField.class));
	}
	return formFields;
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