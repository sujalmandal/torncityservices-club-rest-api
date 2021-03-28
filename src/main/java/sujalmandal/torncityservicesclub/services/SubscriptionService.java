package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.dtos.SubscriptionPaymentDetailsDTO;
import sujalmandal.torncityservicesclub.dtos.SubscriptionRequestDTO;
import sujalmandal.torncityservicesclub.dtos.SubscriptionVerificationRequestDTO;
import sujalmandal.torncityservicesclub.dtos.SubscriptionVerificationResponseDTO;
import sujalmandal.torncityservicesclub.models.Player;

public interface SubscriptionService {
    
    public void removeSubscription(Player player);
    public SubscriptionPaymentDetailsDTO initiateSubscription(SubscriptionRequestDTO request);
    public SubscriptionVerificationResponseDTO verifySubscription(SubscriptionVerificationRequestDTO subVerificationRequest);

}