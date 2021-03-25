package sujalmandal.torncityservicesclub.models;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.SubscriptionType;

@Data
@Document(collection = "Subscription")
public class Subscription {

    @Id
    private String id;
    private SubscriptionType subscriptionType=SubscriptionType.NON_SUBSCRIBED;
    private Player player;
    private LocalDate subscribedOn;
    private List<Payment> payments;

}