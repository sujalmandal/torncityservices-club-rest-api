package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.dtos.commons.SubscriptionPaymentDetailsDTO;
import sujalmandal.torncityservicesclub.dtos.request.SubscriptionRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.SubscriptionVerificationRequestDTO;
import sujalmandal.torncityservicesclub.dtos.response.SubscriptionVerificationResponseDTO;
import sujalmandal.torncityservicesclub.models.Player;

public interface SubscriptionService {
    
    public void removeSubscription(Player player);
    public SubscriptionPaymentDetailsDTO initiateSubscription(SubscriptionRequestDTO request);
    public SubscriptionVerificationResponseDTO verifySubscription(SubscriptionVerificationRequestDTO subVerificationRequest);

}