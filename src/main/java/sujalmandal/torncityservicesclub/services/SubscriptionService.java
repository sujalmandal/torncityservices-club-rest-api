package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.enums.SubscriptionType;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;

public interface SubscriptionService {
    
    public void subscribeForLifeTime(Player player);
    public void subscribeForAMonth(Player player);
    public void removeSubscription(Player player);
    public Subscription initiateSubscription(Player player,SubscriptionType subscriptionType);
    public Subscription verifySubscription(Player player, Subscription subscription);

}