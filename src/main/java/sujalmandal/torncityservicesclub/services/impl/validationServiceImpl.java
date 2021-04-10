package sujalmandal.torncityservicesclub.services.impl;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.annotations.FormField;
import sujalmandal.torncityservicesclub.enums.FormFieldTypeValue;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.JobDetails;
import sujalmandal.torncityservicesclub.services.ValidationService;

@Slf4j
@Service
public class validationServiceImpl implements ValidationService {

    @Override
    public Map<String, String> validateCreateRequest(JobDetails jobDetailImplInstance) {
	if (jobDetailImplInstance == null) {
	    throw new ServiceException("Please select a type of job and fill in the details to create a post!", 400);
	}
	Map<String, String> errorMessages = new HashMap<>();
	JobDetails.getFieldDetails(jobDetailImplInstance.getJobDetailFormTemplateName())
		.forEach((fieldName, formField) -> {
		    try {
			Field currentField = jobDetailImplInstance.getClass().getDeclaredField(fieldName);
			currentField.setAccessible(true);

			String javaType = currentField.getType().getName();
			FormFieldTypeValue fieldType = formField.type();
			Object value = currentField.get(jobDetailImplInstance);

			log.info("validating '{}' of type '{}' with value '{}'", fieldName, javaType, value);

			validateEmptyFields(errorMessages, fieldName, formField, value);

			if (fieldType == FormFieldTypeValue.NUMBER) {
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
			}
			if (fieldType == FormFieldTypeValue.SELECT) {
			    List<String> allowableValues = Arrays.asList(formField.options());
			    validateOptions(errorMessages, fieldName, value, allowableValues);
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
	if (!allowableValues.contains(value)) {
	    errorMessages.put(fieldName, String.format("'%s' is not a valid option for this field", value));
	}
    }

    private void validateEmptyFields(Map<String, String> errorMessages, String fieldName, FormField formField,
	    Object value) {
	if (!formField.optional() && value == null) {
	    errorMessages.put(fieldName, "field cannot be empty");
	}
    }

    private void validateMinMaxValues(Map<String, String> errorMessages, String fieldName, Integer valueAsLong,
	    long minValue, long maxValue) {
	if (valueAsLong < minValue) {
	    errorMessages.put(fieldName,
		    String.format("field has a value smaller than minimum allowed value %s", minValue));
	}
	if (valueAsLong > maxValue) {
	    errorMessages.put(fieldName,
		    String.format("field has a value larger than maximum allowed value of %s", maxValue));
	}
    }

    private void validateMinMaxValues(Map<String, String> errorMessages, String fieldName, Long valueAsLong,
	    long minValue, long maxValue) {
	validateMinMaxValues(errorMessages, fieldName, valueAsLong.intValue(), minValue, maxValue);
    }

}
