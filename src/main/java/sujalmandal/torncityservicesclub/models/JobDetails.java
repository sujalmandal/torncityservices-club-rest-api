package sujalmandal.torncityservicesclub.models;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.utils.PojoUtils;
import sujalmandal.torncityservicesclub.utils.TemplateUtil;

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
	Optional<Class<?>> clazz = TemplateUtil.getJobDetailImplClasses().stream().filter(implClass -> {
	    return implClass.getAnnotation(Template.class).value().getFormTemplateName()
		    .equals(jobDetailFormTemplateName);
	}).findFirst();
	return clazz;
    }

    public static Map<String, TemplateField> getFieldDetails(String jobDetailFormTemplateName) {
	Map<String, TemplateField> formFields = new HashMap<String, TemplateField>();
	for (Field f : getImplementation(jobDetailFormTemplateName).get().getDeclaredFields()) {
	    formFields.put(f.getName(), f.getAnnotation(TemplateField.class));
	}
	return formFields;
    }

    @SuppressWarnings("unchecked")
    default public HashMap<String, Object> toMap() {
	return PojoUtils.getObjectMapper().convertValue(this, HashMap.class);
    }

    default public String getJobDetailFormTemplateName() {
	return this.getClass().getAnnotation(Template.class).value().getFormTemplateName();
    }

    default public String getJobDetailFormTemplateLabel() {
	return this.getClass().getAnnotation(Template.class).value().getFormTemplateLabel();
    }

    default public String getJobDetailFormTemplateLabelForRequest() {
	return this.getClass().getAnnotation(Template.class).value().getFormRequestTypeLabel();
    }

    default public String getJobDetailFormTemplateLabelForOffer() {
	return this.getClass().getAnnotation(Template.class).value().getFormOfferTypeLabel();
    }

    default public String getJobDetailFilterTemplateName() {
	return this.getClass().getAnnotation(Template.class).value().getFilterTemplateName();
    }
}