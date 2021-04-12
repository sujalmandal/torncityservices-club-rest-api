package sujalmandal.torncityservicesclub.dtos.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sujalmandal.torncityservicesclub.constants.SubscriptionType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString
public class SubscriptionRequestDTO extends RequestDTO {
    private String playerId;
    private SubscriptionType subscriptionType;
}