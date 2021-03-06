package sujalmandal.torncityservicesclub.dtos.commons;

import lombok.Data;

@Data
public class SubscriptionPaymentDetailsDTO {
    private String subscriptionId;
    private String paymentLink;
    private String referenceCode;
    private String paymentId;
    private Long amount;
}
