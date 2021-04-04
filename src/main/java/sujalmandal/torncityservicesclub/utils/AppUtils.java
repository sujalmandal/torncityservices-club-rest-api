package sujalmandal.torncityservicesclub.utils;

import static sujalmandal.torncityservicesclub.enums.AppConstants.MONEY_EVENT_PATTERN;
import static sujalmandal.torncityservicesclub.enums.AppConstants.TORN_CASH_PAYMENT_REDIRECT_URL_STUB;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sujalmandal.torncityservicesclub.enums.PaymentStatus;
import sujalmandal.torncityservicesclub.enums.SubscriptionType;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.torn.models.TornPlayerEvent;

public class AppUtils {

    private static final Pattern moneyPattern = Pattern.compile(MONEY_EVENT_PATTERN.toString());

    public static String generateCode() {
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
}
