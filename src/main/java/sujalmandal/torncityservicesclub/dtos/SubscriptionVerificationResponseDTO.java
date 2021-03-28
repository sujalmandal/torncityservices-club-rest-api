package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.PaymentStatus;

@Data
public class SubscriptionVerificationResponseDTO {
    private String playerId;
    private String subscriptionId;
    private PaymentStatus paymentStatus;
    private String paymentId;
}