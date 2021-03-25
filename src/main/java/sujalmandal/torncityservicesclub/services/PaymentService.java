package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.models.Player;

public interface PaymentService {
    
   public void verifyPayment(Player player, String referenceCode);
   public Payment initiatePayment(Player player);

}