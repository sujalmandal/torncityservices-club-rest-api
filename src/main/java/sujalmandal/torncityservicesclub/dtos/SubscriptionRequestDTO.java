package sujalmandal.torncityservicesclub.dtos;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.SubscriptionType;

@Data
public class SubscriptionRequestDTO {
    private String playerId;
    private SubscriptionType subscriptionType;
}