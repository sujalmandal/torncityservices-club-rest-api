package sujalmandal.torncityservicesclub.utils;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.reflections.Reflections;

import com.fasterxml.jackson.core.JsonProcessingException;

import sujalmandal.torncityservicesclub.annotations.HighlightField;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldLabel;
import sujalmandal.torncityservicesclub.annotations.JobDetailFieldType;
import sujalmandal.torncityservicesclub.annotations.JobDetailTemplateKey;
import sujalmandal.torncityservicesclub.annotations.OfferServiceAttribute;
import sujalmandal.torncityservicesclub.annotations.RequestServiceAttribute;
import sujalmandal.torncityservicesclub.dtos.SubscriptionPaymentDetailsDTO;
import sujalmandal.torncityservicesclub.enums.PaymentStatus;
import sujalmandal.torncityservicesclub.enums.ServiceType;
import sujalmandal.torncityservicesclub.enums.SubscriptionType;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.FormFieldDescriptor;
import sujalmandal.torncityservicesclub.models.JobDetailTemplate;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.torn.models.TornPlayerEvent;

public class AppUtils {

    private static final String paymentRedirectedLinkStub = "https://www.torn.com/sendcash.php#/XID=%s";
    private static final Pattern moneyPattern = Pattern.compile("\\$[0-9]+");

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

    public static Set<JobDetailTemplate> generateJobDetailTemplates() throws JsonProcessingException {
	Set<JobDetailTemplate> formDescriptors = new HashSet<>();
	Reflections reflections = new Reflections("sujalmandal.torncityservicesclub.models.jobdetails");
	for (Class<?> clazz : reflections.getTypesAnnotatedWith(JobDetailTemplateKey.class)) {
	    JobDetailTemplate formDescriptor = new JobDetailTemplate();
	    String key = clazz.getAnnotation(JobDetailTemplateKey.class).value().getKey();
	    String label = clazz.getAnnotation(JobDetailTemplateKey.class).value().getLabel();
	    formDescriptor.setKey(key);
	    formDescriptor.setLabel(label);
	    for (Field field : clazz.getDeclaredFields()) {
		String fieldLabel = field.getAnnotation(JobDetailFieldLabel.class).value();
		String fieldType = field.getAnnotation(JobDetailFieldType.class) != null
			? field.getAnnotation(JobDetailFieldType.class).value().toString()
			: null;
		String serviceType = null;
		if (field.isAnnotationPresent(RequestServiceAttribute.class)) {
		    serviceType = ServiceType.REQUESTING.toString();
		} else if (field.isAnnotationPresent(OfferServiceAttribute.class)) {
		    serviceType = ServiceType.OFFERING.toString();
		} else {
		    serviceType = ServiceType.ALL.toString();
		}
		FormFieldDescriptor fieldDescriptor = new FormFieldDescriptor();
		fieldDescriptor.setId(UUID.randomUUID().toString());
		fieldDescriptor.setLabel(fieldLabel);
		fieldDescriptor.setType(fieldType);
		fieldDescriptor.setServiceType(serviceType);
		fieldDescriptor.setName(field.getName());
		if (field.isAnnotationPresent(HighlightField.class)) {
		    fieldDescriptor.setIsHighlighted(Boolean.TRUE);
		    fieldDescriptor
			    .setServiceTypeToHighlightOn(field.getAnnotation(HighlightField.class).value().toString());
		}

		formDescriptor.getElements().add(fieldDescriptor);
	    }
	    formDescriptors.add(formDescriptor);
	}
	return formDescriptors;
    }
}