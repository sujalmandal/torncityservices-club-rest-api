package sujalmandal.torncityservicesclub.services.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.annotations.TemplateField;
import sujalmandal.torncityservicesclub.constants.AppConstants;
import sujalmandal.torncityservicesclub.constants.FieldTypeValue;
import sujalmandal.torncityservicesclub.constants.ServiceTypeValue;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.ServiceDetail;
import sujalmandal.torncityservicesclub.services.ValidationService;

@Slf4j
@Service
public class ValidationServiceImpl implements ValidationService {

    private static final String EMPTY_JOB_TYPE_ERR_MSG = "Please select a type of job and fill in the details to create a post!";
    private static final String MAXIMUM_VALUE_ERR_MSG = "Maximum value allowed is %s";
    private static final String MINIMUM_VALUE_ERR_MSG = "Minimum value allowed is %s";
    private static final String CANNOT_BE_EMPTY_ERR_MSG = "Cannot be empty";
    private static final String INVALID_OPTION_ERR_MSG = "Invalid option '%s'";
    private static final String EMPTY_OPTION_ERR_MSG = "Please select a value from the dropdown.";

    @Override
    public Map<String, String> validateCreateRequest(ServiceDetail serviceDetail, ServiceTypeValue serviceTypeValue) {

	if (serviceDetail == null) {// 013115
	    throw new ServiceException(EMPTY_JOB_TYPE_ERR_MSG, 400);
	}
	Map<String, String> errorMessages = new HashMap<>();
	ServiceDetail.getFieldDetails(serviceDetail.getTemplateMetadata().getTemplateName())
		.forEach((fieldName, formField) -> {
		    try {
			Field currentField = serviceDetail.getClass().getDeclaredField(fieldName);
			currentField.setAccessible(true);

			String javaType = currentField.getType().getName();
			FieldTypeValue fieldType = formField.type();
			Object value = currentField.get(serviceDetail);

			log.info("validating '{}' of type '{}' with value '{}'", fieldName, javaType, value);

			if (fieldType == FieldTypeValue.NUMBER) {
			    validateEmptyFields(errorMessages, fieldName, formField, value, serviceTypeValue);
			    if (javaType.contains(Long.class.getSimpleName())) {
				Long valueAsLong = (Long) value;
				if (valueAsLong != null) {
				    long minValue = formField.minValue();
				    long maxValue = formField.maxValue();
				    validateMinMaxValues(errorMessages, fieldName, valueAsLong, minValue, maxValue);
				}
			    }
			    if (javaType.contains(Integer.class.getSimpleName())) {
				Integer valueAsInt = (Integer) value;
				if (valueAsInt != null) {
				    long minValue = formField.minValue();
				    long maxValue = formField.maxValue();
				    validateMinMaxValues(errorMessages, fieldName, valueAsInt, minValue, maxValue);
				}
			    }
			} else if (fieldType == FieldTypeValue.SELECT) {
			    List<String> allowableValues = Arrays.asList(formField.options());
			    validateOptions(errorMessages, fieldName, value, allowableValues);
			}
			// all other types such as TEXT, CHECKBOX, etc
			else {
			    validateEmptyFields(errorMessages, fieldName, formField, value, serviceTypeValue);
			}

		    } catch (NoSuchFieldException | SecurityException | IllegalArgumentException
			    | IllegalAccessException e) {
			throw new ServiceException(e.getMessage(), 500);
		    }
		});
	return errorMessages;
    }

    private void validateOptions(Map<String, String> errorMessages, String fieldName, Object value,
	    List<String> allowableValues) {
	if (value == null || value.equals("null") || value.equals(AppConstants.SELECT_DUMMY_OPTION)) {
	    errorMessages.put(fieldName, EMPTY_OPTION_ERR_MSG);
	    return;
	}
	if (!allowableValues.contains(value)) {
	    errorMessages.put(fieldName, String.format(INVALID_OPTION_ERR_MSG, value));
	}
    }

    private void validateEmptyFields(Map<String, String> errorMessages, String fieldName, TemplateField formField,
	    Object value, ServiceTypeValue jobServiceType) {
	if (!formField.optional() && value == null) {
	    errorMessages.put(fieldName, CANNOT_BE_EMPTY_ERR_MSG);
	}
    }

    private void validateMinMaxValues(Map<String, String> errorMessages, String fieldName, Integer valueAsLong,
	    long minValue, long maxValue) {
	if (valueAsLong < minValue) {
	    errorMessages.put(fieldName, String.format(MINIMUM_VALUE_ERR_MSG, minValue));
	}
	if (valueAsLong > maxValue) {
	    errorMessages.put(fieldName, String.format(MAXIMUM_VALUE_ERR_MSG, maxValue));
	}
    }

    private void validateMinMaxValues(Map<String, String> errorMessages, String fieldName, Long valueAsLong,
	    long minValue, long maxValue) {
	validateMinMaxValues(errorMessages, fieldName, valueAsLong.intValue(), minValue, maxValue);
    }

}
