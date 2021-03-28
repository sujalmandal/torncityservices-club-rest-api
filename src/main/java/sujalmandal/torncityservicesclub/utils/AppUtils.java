package sujalmandal.torncityservicesclub.utils;

import java.util.UUID;

import sujalmandal.torncityservicesclub.dtos.SubscriptionPaymentDetailsDTO;
import sujalmandal.torncityservicesclub.enums.PaymentStatus;
import sujalmandal.torncityservicesclub.enums.SubscriptionType;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.torn.models.TornPlayerEvent;

public class AppUtils {

    private static final String paymentRedirectedLinkStub = "https://www.torn.com/sendcash.php#/XID=%s";

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
            if (event.getEvent().contains(verificationCode)) {
                Long paymentMade = AppUtils.getAmountFromEventLogs(verificationCode, event);
                
            }
        }
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

}