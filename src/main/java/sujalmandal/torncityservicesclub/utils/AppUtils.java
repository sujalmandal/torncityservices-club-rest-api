package sujalmandal.torncityservicesclub.utils;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.annotations.FieldFormatter;
import sujalmandal.torncityservicesclub.annotations.FilterableField;
import sujalmandal.torncityservicesclub.annotations.HighlightWhen;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.JobDetailTemplate;
import sujalmandal.torncityservicesclub.annotations.ServiceType;
import sujalmandal.torncityservicesclub.dtos.commons.SubscriptionPaymentDetailsDTO;
import sujalmandal.torncityservicesclub.enums.JobDetailFieldTypeValue;
import sujalmandal.torncityservicesclub.enums.PaymentStatus;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.enums.SubscriptionType;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.FilterFieldDescriptor;
import sujalmandal.torncityservicesclub.models.FormFieldDescriptor;
import sujalmandal.torncityservicesclub.models.JobDetailFilterTemplate;
import sujalmandal.torncityservicesclub.models.JobDetailFormTemplate;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.torn.models.TornPlayerEvent;

@Slf4j
public class AppUtils {

    private static final String paymentRedirectedLinkStub = "https://www.torn.com/sendcash.php#/XID=%s";
    private static final Pattern moneyPattern = Pattern.compile("\\$[0-9]+");
    private static final Reflections reflections = new Reflections(
	    "sujalmandal.torncityservicesclub.models.jobdetails");
    private static Set<Class<?>> jobDetailImplClasses;
    private static final String MIN_PREFIX = "min";
    private static final String MAX_PREFIX = "max";

    public static String generateCode() {
	UUID uuid = UUID.randomUUID();
	return uuid.toString();
    }

    public static SubscriptionPaymentDetailsDTO getSubscriptionPaymentDetails(Payment payment) {
	SubscriptionPaymentDetailsDTO subscribeRequestResponseDTO = new SubscriptionPaymentDetailsDTO();
	subscribeRequestResponseDTO.setPaymentLink(getPaymentLink(payment));
	subscribeRequestResponseDTO.setAmount(payment.getAmount());
	subscribeRequestResponseDTO.setReferenceCode(payment.getVerificationCode());
	return subscribeRequestResponseDTO;
    }

    public static String getPaymentLink(Payment payment) {
	if (payment.getStatus() == PaymentStatus.INITIATED) {
	    return String.format(paymentRedirectedLinkStub, payment.getToPlayerId());
	}
	throw new ServiceException("Payment is already finished or cancelled!", 400);
    }

    public static Long getAmountFromEventLogs(String verificationCode, PlayerEventsDTO eventsDTO) {
	for (TornPlayerEvent event : eventsDTO.getEvents()) {
	    if (event.getEventText().contains(verificationCode)) {
		return getAmountFromEvents(event.getEventText());
	    }
	}
	return null;
    }

    public static Long getSubscriptionCost(SubscriptionType subscriptionType) {
	switch (subscriptionType) {
	case LIFE_TIME:
	    return 24_000_000L;
	case ONE_MONTH:
	    return 1_000_000L;
	default:
	    throw new ServiceException("Illegal subscription type!", 400);
	}
    }

    public static Long getAmountFromEvents(String event) {
	event = event.replaceAll(",", "");
	String[] tokens = event.split(":");
	Matcher moneySentMatch = moneyPattern.matcher(tokens[0]);
	if (moneySentMatch.find()) {
	    return Long.parseLong(moneySentMatch.group(0).replace("$", ""));
	}
	return null;
    }

    public static Set<JobDetailFormTemplate> generateJobDetailTemplates() throws JsonProcessingException {
	AppUtils.loadJobDetailImplClasses();
	Set<JobDetailFormTemplate> formDescriptors = new HashSet<>();
	for (Class<?> clazz : jobDetailImplClasses) {
	    JobDetailFormTemplate formDescriptor = new JobDetailFormTemplate();
	    String formTemplateName = clazz.getAnnotation(JobDetailTemplate.class).value().getFormTemplateName();
	    String formTemplateLabel = clazz.getAnnotation(JobDetailTemplate.class).value().getFormTemplateLabel();
	    String filterTemplateName = clazz.getAnnotation(JobDetailTemplate.class).value().getFilterTemplateName();
	    String filterTemplateLabel = clazz.getAnnotation(JobDetailTemplate.class).value().getFilterTemplateLabel();
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
	AppUtils.loadJobDetailImplClasses();
	Set<JobDetailFilterTemplate> filterTemplates = new HashSet<>();
	for (Class<?> clazz : jobDetailImplClasses) {
	    JobDetailFilterTemplate filterTemplate = new JobDetailFilterTemplate();
	    String filterTemplateName = clazz.getAnnotation(JobDetailTemplate.class).value().getFilterTemplateName();
	    String filterTemplateLabel = clazz.getAnnotation(JobDetailTemplate.class).value().getFilterTemplateLabel();
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
			String minFieldName = MIN_PREFIX + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1, fieldName.length());
			String minFieldLabel = filterableField.minFieldLabel();

			String maxFieldName = MAX_PREFIX + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1, fieldName.length());
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
	    AppUtils.jobDetailImplClasses = Collections
		    .unmodifiableSet(reflections.getTypesAnnotatedWith(JobDetailTemplate.class));
	}
    }

}