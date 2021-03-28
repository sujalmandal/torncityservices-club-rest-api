package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;

@Data
public class SubscriptionVerificationRequestDTO {
    private String playerId;
    private String APIKey;
    private String subscriptionId;
    private String paymentId;
}