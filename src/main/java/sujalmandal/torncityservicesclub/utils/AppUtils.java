package sujalmandal.torncityservicesclub.utils;

import static sujalmandal.torncityservicesclub.constants.AppConstants.DD_MM_YYYY_DATE_FORMAT;
import static sujalmandal.torncityservicesclub.constants.AppConstants.MONEY_EVENT_PATTERN;
import static sujalmandal.torncityservicesclub.constants.AppConstants.TORN_CASH_PAYMENT_REDIRECT_URL_STUB;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sujalmandal.torncityservicesclub.constants.PaymentStatus;
import sujalmandal.torncityservicesclub.constants.SubscriptionType;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.torn.models.TornPlayerEvent;

public class AppUtils {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DD_MM_YYYY_DATE_FORMAT.toString());
    private static final Pattern moneyPattern = Pattern.compile(MONEY_EVENT_PATTERN.toString());

    public static String generateUUID() {
	UUID uuid = UUID.randomUUID();
	return uuid.toString();
    }

    public static String getPaymentLink(Payment payment) {
	if (payment.getStatus() == PaymentStatus.INITIATED) {
	    return String.format(TORN_CASH_PAYMENT_REDIRECT_URL_STUB.toString(), payment.getToPlayerId());
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

    public static String dateToDD_MM_YYYYString(LocalDateTime localDateTime) {
	if (localDateTime != null) {
	    return formatter.format(localDateTime);
	}
	return "";
    }
}
