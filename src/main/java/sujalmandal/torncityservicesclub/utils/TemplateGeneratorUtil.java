package sujalmandal.torncityservicesclub.utils;

import static sujalmandal.torncityservicesclub.enums.AppConstants.JOB_DETAIL_IMPL_SEARCH_PACKAGE;
import static sujalmandal.torncityservicesclub.enums.AppConstants.NUMBER_TYPE_FIELD_MAX_PREFIX;
import static sujalmandal.torncityservicesclub.enums.AppConstants.NUMBER_TYPE_FIELD_MIN_PREFIX;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.ServiceType;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.FilterFieldDescriptor;
import sujalmandal.torncityservicesclub.models.FormFieldDescriptor;
import sujalmandal.torncityservicesclub.models.JobDetailFilterTemplate;
import sujalmandal.torncityservicesclub.models.JobDetailFormTemplate;

@Slf4j
public class TemplateGeneratorUtil {

    private static final Reflections reflections = new Reflections(JOB_DETAIL_IMPL_SEARCH_PACKAGE.toString());
    private static Set<Class<?>> jobDetailImplClasses;

    public static Set<JobDetailFormTemplate> generateJobDetailFormTemplates() throws JsonProcessingException {
	TemplateGeneratorUtil.loadJobDetailImplClasses();
	Set<JobDetailFormTemplate> formDescriptors = new HashSet<>();
	for (Class<?> clazz : jobDetailImplClasses) {
	    JobDetailFormTemplate formDescriptor = new JobDetailFormTemplate();
	    String formTemplateName = clazz.getAnnotation(GenerateTemplate.class).value().getFormTemplateName();
	    String formTemplateLabel = clazz.getAnnotation(GenerateTemplate.class).value().getFormTemplateLabel();
	    String filterTemplateName = clazz.getAnnotation(GenerateTemplate.class).value().getFilterTemplateName();
	    String filterTemplateLabel = clazz.getAnnotation(GenerateTemplate.class).value().getFilterTemplateLabel();
	    formDescriptor.setFormTemplateName(formTemplateName);
	    formDescriptor.setFormTemplateLabel(formTemplateLabel);
	    formDescriptor.setFilterTemplateName(filterTemplateName);
	    formDescriptor.setFilterTemplateLabel(filterTemplateLabel);
	    for (Field field : clazz.getDeclaredFields()) {
		FormFieldDescriptor fieldDescriptor = new FormFieldDescriptor();
		String fieldLabel = null;
		if (field.isAnnotationPresent(JobDetailFieldLabel.class)) {
		    fieldLabel = field.getAnnotation(JobDetailFieldLabel.class).value();
		} else {
		    throw new ServiceException(String.format("{%s} of {%s} missing a mandatory annotation!",
			    field.getName(), clazz.getSimpleName()));
		}
		String fieldType = field.getAnnotation(JobDetailFieldType.class) != null
			? field.getAnnotation(JobDetailFieldType.class).value().toString()
			: null;
		ServiceTypeValue serviceType = null;
		if (field.isAnnotationPresent(ServiceType.class)) {
		    serviceType = field.getAnnotation(ServiceType.class).value();
		} else {
		    serviceType = ServiceTypeValue.ALL;
		}

		fieldDescriptor.setId(UUID.randomUUID().toString());
		fieldDescriptor.setLabel(fieldLabel);
		fieldDescriptor.setType(fieldType);
		fieldDescriptor.setServiceType(serviceType);
		fieldDescriptor.setName(field.getName());
		if (field.isAnnotationPresent(HighlightWhen.class)) {
		    fieldDescriptor.setIsHighlighted(Boolean.TRUE);
		    fieldDescriptor
			    .setServiceTypeToHighlightOn(field.getAnnotation(HighlightWhen.class).value().toString());
		}
		if (field.isAnnotationPresent(FieldFormatter.class)) {
		    fieldDescriptor.setFormat(field.getAnnotation(FieldFormatter.class).value().toString());
		}
		formDescriptor.getElements().add(fieldDescriptor);
	    }
	    formDescriptors.add(formDescriptor);
	}
	return formDescriptors;
    }

    public static Set<JobDetailFilterTemplate> generateJobDetailFilterTemplates() throws JsonProcessingException {
	TemplateGeneratorUtil.loadJobDetailImplClasses();
	Set<JobDetailFilterTemplate> filterTemplates = new HashSet<>();
	for (Class<?> clazz : jobDetailImplClasses) {
	    JobDetailFilterTemplate filterTemplate = new JobDetailFilterTemplate();
	    String filterTemplateName = clazz.getAnnotation(GenerateTemplate.class).value().getFilterTemplateName();
	    String filterTemplateLabel = clazz.getAnnotation(GenerateTemplate.class).value().getFilterTemplateLabel();
	    filterTemplate.setFilterTemplateName(filterTemplateName);
	    filterTemplate.setFilterTemplateLabel(filterTemplateLabel);
	    for (Field field : clazz.getDeclaredFields()) {

		boolean isFilterable = field.isAnnotationPresent(FilterableField.class);
		if (isFilterable) {
		    FilterableField filterableField = field.getAnnotation(FilterableField.class);

		    JobDetailFieldTypeValue fieldType = field.getAnnotation(JobDetailFieldType.class) != null
			    ? field.getAnnotation(JobDetailFieldType.class).value()
			    : null;
		    String fieldName = field.getName();
		    String fieldLabel = filterableField.label();
		    ServiceTypeValue serviceType = null;
		    if (field.isAnnotationPresent(ServiceType.class)) {
			serviceType = field.getAnnotation(ServiceType.class).value();
		    } else {
			serviceType = ServiceTypeValue.ALL;
		    }
		    String format = null;
		    if (field.isAnnotationPresent(FieldFormatter.class)) {
			format = field.getAnnotation(FieldFormatter.class).value().toString();
		    }
		    if (fieldType == JobDetailFieldTypeValue.NUMBER) {
			String limit = filterableField.limit().replaceAll("_", "");
			String minFieldName = NUMBER_TYPE_FIELD_MIN_PREFIX.toString()
				+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
			String minFieldLabel = filterableField.minFieldLabel();

			String maxFieldName = NUMBER_TYPE_FIELD_MAX_PREFIX.toString()
				+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
			String maxFieldLabel = filterableField.maxFieldLabel();

			FilterFieldDescriptor minFieldDescriptor = new FilterFieldDescriptor(serviceType,
				fieldType.toString(), minFieldName, minFieldLabel);
			minFieldDescriptor.setLimit(limit);
			minFieldDescriptor.setFormat(format);
			minFieldDescriptor.setGroupName(fieldName);

			FilterFieldDescriptor maxFieldDescriptor = new FilterFieldDescriptor(serviceType,
				fieldType.toString(), maxFieldName, maxFieldLabel);
			maxFieldDescriptor.setLimit(limit);
			maxFieldDescriptor.setFormat(format);
			maxFieldDescriptor.setGroupName(fieldName);

			filterTemplate.getFilterElements().add(minFieldDescriptor);
			filterTemplate.getFilterElements().add(maxFieldDescriptor);
		    } else {
			FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor(serviceType,
				fieldType.toString(), fieldName, fieldLabel);
			fieldDescriptor.setGroupName(fieldName);
			filterTemplate.getFilterElements().add(fieldDescriptor);
		    }
		}
	    }
	    filterTemplates.add(filterTemplate);
	}
	return filterTemplates;
    }

    public static Set<Class<?>> getJobDetailImplClasses() {
	return jobDetailImplClasses;
    }

    public static <V, K> void loadJobDetailImplClasses() {
	if (jobDetailImplClasses == null) {
	    log.info("loading JobDetail implementation classes..");
	    TemplateGeneratorUtil.jobDetailImplClasses = Collections
		    .unmodifiableSet(reflections.getTypesAnnotatedWith(GenerateTemplate.class));
	}
    }

}