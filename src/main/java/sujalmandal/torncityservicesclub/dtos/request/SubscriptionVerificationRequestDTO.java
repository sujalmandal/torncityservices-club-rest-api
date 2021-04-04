package sujalmandal.torncityservicesclub.dtos.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SubscriptionVerificationRequestDTO extends RequestDTO {
    private String playerId;
    private String subscriptionId;
    private String paymentId;
}