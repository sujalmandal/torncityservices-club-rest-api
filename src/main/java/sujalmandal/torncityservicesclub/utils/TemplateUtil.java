package sujalmandal.torncityservicesclub.utils;

import static sujalmandal.torncityservicesclub.constants.AppConstants.SERVICE_DETAIL_IMPL_SEARCH_PACKAGE;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.annotations.Templatized;
import sujalmandal.torncityservicesclub.constants.AppConstants;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.TemplateValue;
import sujalmandal.torncityservicesclub.dtos.commons.TemplateMetaInfo;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.FilterFieldDescriptor;
import sujalmandal.torncityservicesclub.models.FormFieldDescriptor;
import sujalmandal.torncityservicesclub.models.ServiceDetailTemplate;
import sujalmandal.torncityservicesclub.models.Template;
import sujalmandal.torncityservicesclub.models.ViewFieldDescriptor;

@Slf4j
public class TemplateUtil {

    private static final Reflections reflections = new Reflections(SERVICE_DETAIL_IMPL_SEARCH_PACKAGE.toString());

    private static Set<Class<?>> templateableClasses;

    public static Set<Template> getTemplatesFromClasses() {
	loadTemplatizedClasses();
	Set<Template> templates = new HashSet<>();
	for (Class<?> clazz : templateableClasses) {
	    ServiceDetailTemplate template = new ServiceDetailTemplate();
	    TemplateValue templateValue = clazz.getAnnotation(Templatized.class).value();
	    template.setTemplateInfo(getTemplateMetaInfo(templateValue));
	    for (Field field : clazz.getDeclaredFields()) {
		field.setAccessible(true);
		if (field.isAnnotationPresent(TemplateField.class)) {
		    TemplateField templateField = field.getAnnotation(TemplateField.class);
		    extractFormFieldDescriptor(field, template, templateField);
		    extractFilterFieldDescriptor(field, template, templateField);
		    extractViewFieldDescriptor(field, template, templateField);
		} else {
		    throw new ServiceException(String.format("{%s} of {%s} missing a mandatory annotation!",
			    field.getName(), clazz.getSimpleName()));
		}
	    }
	    templates.add(template);
	}
	return templates;
    }

    private static void extractViewFieldDescriptor(Field field, ServiceDetailTemplate template,
	    TemplateField templateField) {
	ViewFieldDescriptor viewFieldDescriptor = new ViewFieldDescriptor();
	viewFieldDescriptor.setFormat(templateField.formatter());
	viewFieldDescriptor.setLabel(templateField.viewLabel());
	viewFieldDescriptor.setType(templateField.type());
	template.getViewElements().add(viewFieldDescriptor);
    }

    private static void extractFormFieldDescriptor(Field field, ServiceDetailTemplate template,
	    TemplateField templateField) {
	FormFieldDescriptor fieldDescriptor = new FormFieldDescriptor();
	fieldDescriptor.setId(AppUtils.generateUUID());
	fieldDescriptor.setLabel(templateField.formLabel());
	fieldDescriptor.setType(templateField.type());
	fieldDescriptor.setName(field.getName());
	fieldDescriptor.setMaxValue(templateField.maxValue());
	fieldDescriptor.setMinValue(templateField.minValue());
	fieldDescriptor.setDefaultValue(templateField.defaultValue());
	fieldDescriptor.setOptions(Arrays.asList(templateField.options()));
	fieldDescriptor.setFormat(templateField.formatter());
	if (fieldDescriptor.getType() == FieldTypeValue.NUMBER) {
	    fieldDescriptor.setFormat(templateField.formatter());
	}
	template.getFormElements().add(fieldDescriptor);
    }

    private static void extractFilterFieldDescriptor(Field field, ServiceDetailTemplate template,
	    TemplateField templateField) {

	if (templateField.isSearchable()) {
	    switch (templateField.type()) {
	    case NUMBER:
		extractNumberFilterFields(field, template, templateField);
		break;
	    case CHECKBOX:
		extractCheckboxFilterField(field, template, templateField);
		break;
	    case SELECT:
		extractSelectFilterField(field, template, templateField);
		break;
	    default:
		extractTextFilterField(field, template, templateField);
	    }
	}

    }

    private static void extractSelectFilterField(Field field, ServiceDetailTemplate template,
	    TemplateField templateField) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(field.getName());
	fieldDescriptor.setName(field.getName());
	fieldDescriptor.setLabel(templateField.filterCommonLabel());
	fieldDescriptor.setDefaultValue(templateField.defaultValue());
	fieldDescriptor.setFormat(templateField.formatter());
	fieldDescriptor.setOptions(Arrays.asList(templateField.options()));
	fieldDescriptor.setType(templateField.type());
	template.getFilterElements().add(fieldDescriptor);
    }

    private static void extractCheckboxFilterField(Field field, ServiceDetailTemplate template,
	    TemplateField templateField) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(field.getName());
	fieldDescriptor.setName(field.getName());
	fieldDescriptor.setFormat(templateField.formatter());
	fieldDescriptor.setLabel(templateField.filterCommonLabel());
	fieldDescriptor.setDefaultValue(templateField.defaultValue());
	fieldDescriptor.setType(templateField.type());
	template.getFilterElements().add(fieldDescriptor);
    }

    private static void extractTextFilterField(Field field, ServiceDetailTemplate template,
	    TemplateField templateField) {
	FilterFieldDescriptor fieldDescriptor = new FilterFieldDescriptor();
	fieldDescriptor.setGroupName(field.getName());
	fieldDescriptor.setName(field.getName());
	fieldDescriptor.setFormat(templateField.formatter());
	fieldDescriptor.setDefaultValue(templateField.defaultValue());
	fieldDescriptor.setLabel(templateField.filterCommonLabel());
	fieldDescriptor.setType(templateField.type());
	template.getFilterElements().add(fieldDescriptor);
    }

    private static void extractNumberFilterFields(Field field, ServiceDetailTemplate template,
	    TemplateField templateField) {
	String fieldName = field.getName();

	String maxFieldName = AppConstants.NUMBER_TYPE_FIELD_MAX_PREFIX + fieldName.substring(0, 1).toUpperCase()
		+ fieldName.substring(1, fieldName.length());

	String minFieldName = AppConstants.NUMBER_TYPE_FIELD_MIN_PREFIX + fieldName.substring(0, 1).toUpperCase()
		+ fieldName.substring(1, fieldName.length());

	FilterFieldDescriptor minFieldDescriptor = new FilterFieldDescriptor();
	minFieldDescriptor.setMaxValue(templateField.maxValue());
	minFieldDescriptor.setMinValue(templateField.minValue());
	minFieldDescriptor.setFormat(templateField.formatter());
	minFieldDescriptor.setName(minFieldName);
	minFieldDescriptor.setLabel(templateField.filterMinFieldLabel());
	minFieldDescriptor.setGroupName(fieldName);
	minFieldDescriptor.setDefaultValue(templateField.defaultValue());
	minFieldDescriptor.setType(templateField.type());

	FilterFieldDescriptor maxFieldDescriptor = new FilterFieldDescriptor();
	maxFieldDescriptor.setMaxValue(templateField.maxValue());
	maxFieldDescriptor.setMinValue(templateField.minValue());
	maxFieldDescriptor.setFormat(templateField.formatter());
	maxFieldDescriptor.setName(maxFieldName);
	maxFieldDescriptor.setLabel(templateField.filterMaxFieldLabel());
	maxFieldDescriptor.setGroupName(fieldName);
	maxFieldDescriptor.setDefaultValue(templateField.defaultValue());
	maxFieldDescriptor.setType(templateField.type());

	template.getFilterElements().add(minFieldDescriptor);
	template.getFilterElements().add(maxFieldDescriptor);
    }

    public static Set<Class<?>> getTemplatizedClasses() {
	return templateableClasses;
    }

    public static <V, K> void loadTemplatizedClasses() {
	if (templateableClasses == null) {
	    log.info("loading classed marked with @Templatized...");
	    TemplateUtil.templateableClasses = Collections
		    .unmodifiableSet(reflections.getTypesAnnotatedWith(Templatized.class));
	}
    }

    private static TemplateMetaInfo getTemplateMetaInfo(TemplateValue templateValue) {
	return PojoUtils.map(templateValue, new TemplateMetaInfo());
    }
}