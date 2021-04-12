package sujalmandal.torncityservicesclub.utils;

import static sujalmandal.torncityservicesclub.constants.AppConstants.JOB_DETAIL_IMPL_SEARCH_PACKAGE;
import static sujalmandal.torncityservicesclub.constants.AppConstants.NUMBER_TYPE_FIELD_MAX_PREFIX;
import static sujalmandal.torncityservicesclub.constants.AppConstants.NUMBER_TYPE_FIELD_MIN_PREFIX;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.annotations.Template;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.constants.FieldFormatValue;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.FilterFieldDescriptor;
import sujalmandal.torncityservicesclub.models.FilterTemplate;
import sujalmandal.torncityservicesclub.models.FormFieldDescriptor;
import sujalmandal.torncityservicesclub.models.FormTemplate;

@Slf4j
public class TemplateUtil {

    private static final Reflections reflections = new Reflections(JOB_DETAIL_IMPL_SEARCH_PACKAGE.toString());

    private static Set<Class<?>> templateableClasses;

    public static Set<FormTemplate> generateFormTemplate() throws JsonProcessingException {
	TemplateUtil.loadTemplatizedClasses();
	Set<FormTemplate> formDescriptors = new HashSet<>();
	for (Class<?> clazz : templateableClasses) {
	    FormTemplate formDescriptor = new FormTemplate();
	    TemplateValue template = clazz.getAnnotation(Template.class).value();

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
		if (field.isAnnotationPresent(TemplateField.class)) {
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
	TemplateField formField = field.getAnnotation(TemplateField.class);
	ServiceTypeValue serviceType = formField.serviceType();
	fieldDescriptor.setId(UUID.randomUUID().toString());
	fieldDescriptor.setLabel(formField.labelCommon());
	fieldDescriptor.setViewLabelRequest(formField.viewLabelRequest());
	fieldDescriptor.setViewLabelOffer(formField.viewLabelOffer());
	fieldDescriptor.setLabelRequest(formField.labelRequest());
	fieldDescriptor.setLabelOffer(formField.labelOffer());
	fieldDescriptor.setType(formField.type());
	fieldDescriptor.setServiceType(serviceType);
	fieldDescriptor.setName(field.getName());
	fieldDescriptor.setMaxValue(formField.maxValue());
	fieldDescriptor.setMinValue(formField.minValue());
	fieldDescriptor.setDefaultValue(formField.defaultValue());
	fieldDescriptor.setOptions(Arrays.asList(formField.options()));
	if (fieldDescriptor.getType() == FieldTypeValue.NUMBER) {
	    fieldDescriptor.setFormat(formField.formatter());
	}
    }

    public static Set<FilterTemplate> generateFilterTemplate() throws JsonProcessingException {
	TemplateUtil.loadTemplatizedClasses();
	Set<FilterTemplate> filterTemplates = new HashSet<>();
	for (Class<?> clazz : templateableClasses) {
	    FilterTemplate filterTemplate = new FilterTemplate();
	    String filterTemplateName = clazz.getAnnotation(Template.class).value().getFilterTemplateName();
	    String filterTemplateLabel = clazz.getAnnotation(Template.class).value().getFilterTemplateLabel();
	    filterTemplate.setFilterTemplateName(filterTemplateName);
	    filterTemplate.setFilterTemplateLabel(filterTemplateLabel);

	    String filterRequestTypeLabel = clazz.getAnnotation(Template.class).value().getFilterRequestTypeLabel();
	    String filterOfferTypeLabel = clazz.getAnnotation(Template.class).value().getFilterOfferTypeLabel();
	    filterTemplate.setFilterRequestTypeLabel(filterRequestTypeLabel);
	    filterTemplate.setFilterOfferTypeLabel(filterOfferTypeLabel);

	    for (Field field : clazz.getDeclaredFields()) {

		TemplateField templateField = field.getAnnotation(TemplateField.class);
		if (templateField.isSearchable()) {
		    if (field.isAnnotationPresent(TemplateField.class)) {

			FieldTypeValue fieldType = templateField.type();
			String fieldName = field.getName();
			String fieldLabel = templateField.labelCommon();
			String defaultValue = templateField.defaultValue();
			ServiceTypeValue serviceType = templateField.serviceType();
			FieldFormatValue format = templateField.formatter();

			switch (fieldType) {
			case NUMBER:
			    long maxValue = templateField.maxValue();
			    long minValue = templateField.minValue();
			    extractNumberFilterFields(filterTemplate, fieldType, templateField.maxFieldLabel(),
				    templateField.minFieldLabel(), fieldName, serviceType, format, defaultValue,
				    maxValue, minValue, templateField.labelOffer(), templateField.labelRequest());
			    break;
			case CHECKBOX:
			    extractCheckboxFilterField(filterTemplate, fieldType, fieldName, serviceType, fieldLabel,
				    defaultValue, templateField.labelOffer(), templateField.labelRequest());
			    break;
			case SELECT:
			    String[] options = templateField.options();
			    extractSelectFilterField(filterTemplate, fieldType, fieldName, serviceType, fieldLabel,
				    defaultValue, options, templateField.labelOffer(), templateField.labelRequest());
			    break;
			default:
			    extractTextFilterField(filterTemplate, fieldType, fieldName, serviceType, fieldLabel,
				    defaultValue, templateField.labelOffer(), templateField.labelRequest());
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

    private static void extractSelectFilterField(FilterTemplate filterTemplate, FieldTypeValue type, String fieldName,
	    ServiceTypeValue serviceType, String fieldLabel, String defaultValue, String[] options, String labelOffer,
	    String labelRequest) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(fieldName);
	fieldDescriptor.setFieldName(fieldName);
	fieldDescriptor.setFieldLabel(fieldLabel);
	fieldDescriptor.setDefaultValue(defaultValue);
	fieldDescriptor.setOptions(Arrays.asList(options));
	fieldDescriptor.setServiceType(serviceType);
	fieldDescriptor.setFieldType(type);
	fieldDescriptor.setLabelOffer(labelOffer);
	fieldDescriptor.setLabelRequest(labelRequest);
	filterTemplate.getFilterElements().add(fieldDescriptor);
    }

    private static void extractCheckboxFilterField(FilterTemplate filterTemplate, FieldTypeValue type, String fieldName,
	    ServiceTypeValue serviceType, String fieldLabel, String defaultValue, String labelOffer,
	    String labelRequest) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(fieldName);
	fieldDescriptor.setFieldName(fieldName);
	fieldDescriptor.setFieldLabel(fieldLabel);
	fieldDescriptor.setDefaultValue(defaultValue);
	fieldDescriptor.setServiceType(serviceType);
	fieldDescriptor.setFieldType(type);
	fieldDescriptor.setLabelOffer(labelOffer);
	fieldDescriptor.setLabelRequest(labelRequest);
	filterTemplate.getFilterElements().add(fieldDescriptor);
    }

    private static void extractTextFilterField(FilterTemplate filterTemplate, FieldTypeValue type, String fieldName,
	    ServiceTypeValue serviceType, String fieldLabel, String defaultValue, String labelOffer,
	    String labelRequest) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(fieldName);
	fieldDescriptor.setFieldName(fieldName);
	fieldDescriptor.setDefaultValue(defaultValue);
	fieldDescriptor.setFieldLabel(fieldLabel);
	fieldDescriptor.setServiceType(serviceType);
	fieldDescriptor.setFieldType(type);
	fieldDescriptor.setLabelOffer(labelOffer);
	fieldDescriptor.setLabelRequest(labelRequest);
	filterTemplate.getFilterElements().add(fieldDescriptor);
    }

    private static void extractNumberFilterFields(FilterTemplate filterTemplate, FieldTypeValue type,
	    String maxFieldLabel, String minFieldLabel, String fieldName, ServiceTypeValue serviceType,
	    FieldFormatValue format, String defaultValue, long maxValue, long minValue, String labelOffer,
	    String labelRequest) {

	String maxFieldName = NUMBER_TYPE_FIELD_MAX_PREFIX.toString() + fieldName.substring(0, 1).toUpperCase()
		+ fieldName.substring(1, fieldName.length());

	String minFieldName = NUMBER_TYPE_FIELD_MIN_PREFIX.toString() + fieldName.substring(0, 1).toUpperCase()
		+ fieldName.substring(1, fieldName.length());

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
	minFieldDescriptor.setLabelOffer(labelOffer);
	minFieldDescriptor.setLabelRequest(labelRequest);

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
	maxFieldDescriptor.setLabelOffer(labelOffer);
	maxFieldDescriptor.setLabelRequest(labelRequest);

	filterTemplate.getFilterElements().add(minFieldDescriptor);
	filterTemplate.getFilterElements().add(maxFieldDescriptor);
    }

    public static Set<Class<?>> getJobDetailImplClasses() {
	return templateableClasses;
    }

    public static <V, K> void loadTemplatizedClasses() {
	if (templateableClasses == null) {
	    log.info("loading classed marked with @Templateable...");
	    TemplateUtil.templateableClasses = Collections
		    .unmodifiableSet(reflections.getTypesAnnotatedWith(Template.class));
	}
    }

}