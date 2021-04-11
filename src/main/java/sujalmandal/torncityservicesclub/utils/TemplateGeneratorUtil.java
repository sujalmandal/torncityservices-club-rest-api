package sujalmandal.torncityservicesclub.utils;

import static sujalmandal.torncityservicesclub.enums.AppConstants.JOB_DETAIL_IMPL_SEARCH_PACKAGE;
import static sujalmandal.torncityservicesclub.enums.AppConstants.NUMBER_TYPE_FIELD_MAX_PREFIX;
import static sujalmandal.torncityservicesclub.enums.AppConstants.NUMBER_TYPE_FIELD_MIN_PREFIX;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.annotations.GenerateTemplate;
import sujalmandal.torncityservicesclub.enums.FieldFormatterValue;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.JobDetailTemplateValue;
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
	    JobDetailTemplateValue template = clazz.getAnnotation(GenerateTemplate.class).value();

	    String formTemplateName = template.getFormTemplateName();
	    String formTemplateLabel = template.getFormTemplateLabel();
	    String filterTemplateName = template.getFilterTemplateName();
	    String filterTemplateLabel = template.getFilterTemplateLabel();

	    String formRequestTypeLabel = template.getFormRequestTypeLabel();
	    String formOfferTypeLabel = template.getFormOfferTypeLabel();

	    formDescriptor.setFormTemplateName(formTemplateName);
	    formDescriptor.setFormTemplateLabel(formTemplateLabel);
	    formDescriptor.setFilterTemplateName(filterTemplateName);
	    formDescriptor.setFilterTemplateLabel(filterTemplateLabel);

	    formDescriptor.setFormRequestTypeLabel(formRequestTypeLabel);
	    formDescriptor.setFormOfferTypeLabel(formOfferTypeLabel);

	    for (Field field : clazz.getDeclaredFields()) {
		FormFieldDescriptor fieldDescriptor = new FormFieldDescriptor();
		if (field.isAnnotationPresent(FormField.class)) {
		    extractFormFieldDescriptor(field, fieldDescriptor);
		    formDescriptor.getElements().add(fieldDescriptor);
		} else {
		    throw new ServiceException(String.format("{%s} of {%s} missing a mandatory annotation!",
			    field.getName(), clazz.getSimpleName()));
		}
	    }
	    formDescriptors.add(formDescriptor);
	}
	return formDescriptors;
    }

    private static void extractFormFieldDescriptor(Field field, FormFieldDescriptor fieldDescriptor) {
	FormField formField = field.getAnnotation(FormField.class);
	ServiceTypeValue serviceType = formField.serviceType();
	fieldDescriptor.setId(UUID.randomUUID().toString());
	fieldDescriptor.setLabel(formField.label());
	fieldDescriptor.setType(formField.type());
	fieldDescriptor.setServiceType(serviceType);
	fieldDescriptor.setName(field.getName());
	fieldDescriptor.setMaxValue(formField.maxValue());
	fieldDescriptor.setMinValue(formField.minValue());
	fieldDescriptor.setDefaultValue(formField.defaultValue());
	fieldDescriptor.setOptions(Arrays.asList(formField.options()));
	if (fieldDescriptor.getType() == FormFieldTypeValue.NUMBER) {
	    fieldDescriptor.setFormat(formField.formatter());
	}
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

	    String filterRequestTypeLabel = clazz.getAnnotation(GenerateTemplate.class).value()
		    .getFilterRequestTypeLabel();
	    String filterOfferTypeLabel = clazz.getAnnotation(GenerateTemplate.class).value().getFilterOfferTypeLabel();
	    filterTemplate.setFilterRequestTypeLabel(filterRequestTypeLabel);
	    filterTemplate.setFilterOfferTypeLabel(filterOfferTypeLabel);

	    for (Field field : clazz.getDeclaredFields()) {

		boolean isFilterable = field.isAnnotationPresent(FilterableField.class);
		if (isFilterable) {
		    if (field.isAnnotationPresent(FormField.class)) {
			FormField formField = field.getAnnotation(FormField.class);
			FilterableField filterableField = field.getAnnotation(FilterableField.class);

			FormFieldTypeValue fieldType = formField.type();
			String fieldName = field.getName();
			String fieldLabel = filterableField.label();
			String defaultValue = formField.defaultValue();
			ServiceTypeValue serviceType = formField.serviceType();
			FieldFormatterValue format = formField.formatter();

			switch (fieldType) {
			case NUMBER:
			    long maxValue = formField.maxValue();
			    long minValue = formField.minValue();
			    extractNumberFilterFields(filterTemplate, fieldType, filterableField, fieldName,
				    serviceType, format, defaultValue, maxValue, minValue);
			    break;
			case CHECKBOX:
			    extractCheckboxFilterField(filterTemplate, fieldType, fieldName, serviceType, fieldLabel,
				    defaultValue);
			    break;
			case SELECT:
			    String[] options = formField.options();
			    extractSelectFilterField(filterTemplate, fieldType, fieldName, serviceType, fieldLabel,
				    defaultValue, options);
			    break;
			default:
			    extractTextFilterField(filterTemplate, fieldType, fieldName, serviceType, fieldLabel,
				    defaultValue);
			}

		    } else {
			throw new ServiceException(String.format("{%s} of {%s} missing a mandatory annotation!",
				field.getName(), clazz.getSimpleName()));
		    }
		}
	    }
	    filterTemplates.add(filterTemplate);
	}
	return filterTemplates;
    }

    private static void extractSelectFilterField(JobDetailFilterTemplate filterTemplate, FormFieldTypeValue type,
	    String fieldName, ServiceTypeValue serviceType, String fieldLabel, String defaultValue, String[] options) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(fieldName);
	fieldDescriptor.setFieldName(fieldName);
	fieldDescriptor.setFieldLabel(fieldLabel);
	fieldDescriptor.setDefaultValue(defaultValue);
	fieldDescriptor.setOptions(Arrays.asList(options));
	fieldDescriptor.setServiceType(serviceType);
	fieldDescriptor.setFieldType(type);
	filterTemplate.getFilterElements().add(fieldDescriptor);
    }

    private static void extractCheckboxFilterField(JobDetailFilterTemplate filterTemplate, FormFieldTypeValue type,
	    String fieldName, ServiceTypeValue serviceType, String fieldLabel, String defaultValue) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(fieldName);
	fieldDescriptor.setFieldName(fieldName);
	fieldDescriptor.setFieldLabel(fieldLabel);
	fieldDescriptor.setDefaultValue(defaultValue);
	fieldDescriptor.setServiceType(serviceType);
	fieldDescriptor.setFieldType(type);
	filterTemplate.getFilterElements().add(fieldDescriptor);
    }

    private static void extractTextFilterField(JobDetailFilterTemplate filterTemplate, FormFieldTypeValue type,
	    String fieldName, ServiceTypeValue serviceType, String fieldLabel, String defaultValue) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(fieldName);
	fieldDescriptor.setFieldName(fieldName);
	fieldDescriptor.setDefaultValue(defaultValue);
	fieldDescriptor.setFieldLabel(fieldLabel);
	fieldDescriptor.setServiceType(serviceType);
	fieldDescriptor.setFieldType(type);
	filterTemplate.getFilterElements().add(fieldDescriptor);
    }

    private static void extractNumberFilterFields(JobDetailFilterTemplate filterTemplate, FormFieldTypeValue type,
	    FilterableField filterableField, String fieldName, ServiceTypeValue serviceType, FieldFormatterValue format,
	    String defaultValue, long maxValue, long minValue) {
	String maxFieldName = NUMBER_TYPE_FIELD_MAX_PREFIX.toString() + fieldName.substring(0, 1).toUpperCase()
		+ fieldName.substring(1, fieldName.length());
	String maxFieldLabel = filterableField.maxFieldLabel();

	String minFieldName = NUMBER_TYPE_FIELD_MIN_PREFIX.toString() + fieldName.substring(0, 1).toUpperCase()
		+ fieldName.substring(1, fieldName.length());
	String minFieldLabel = filterableField.minFieldLabel();

	FilterFieldDescriptor minFieldDescriptor = new FilterFieldDescriptor();
	minFieldDescriptor.setMaxValue(maxValue);
	minFieldDescriptor.setMinValue(minValue);
	minFieldDescriptor.setFormat(format);
	minFieldDescriptor.setFieldName(minFieldName);
	minFieldDescriptor.setFieldLabel(minFieldLabel);
	minFieldDescriptor.setGroupName(fieldName);
	minFieldDescriptor.setServiceType(serviceType);
	minFieldDescriptor.setDefaultValue(defaultValue);
	minFieldDescriptor.setFieldType(type);

	FilterFieldDescriptor maxFieldDescriptor = new FilterFieldDescriptor();
	maxFieldDescriptor.setMaxValue(maxValue);
	maxFieldDescriptor.setMinValue(minValue);
	maxFieldDescriptor.setFormat(format);
	maxFieldDescriptor.setFieldName(maxFieldName);
	maxFieldDescriptor.setFieldLabel(maxFieldLabel);
	maxFieldDescriptor.setGroupName(fieldName);
	maxFieldDescriptor.setServiceType(serviceType);
	maxFieldDescriptor.setDefaultValue(defaultValue);
	maxFieldDescriptor.setFieldType(type);

	filterTemplate.getFilterElements().add(minFieldDescriptor);
	filterTemplate.getFilterElements().add(maxFieldDescriptor);
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