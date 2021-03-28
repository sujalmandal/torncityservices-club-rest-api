package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.models.Payment;
public interface PaymentService {
   
   public Payment verifyPaymentOnSenderSide(String paymentId,String senderId,String senderAPIKey);
   public Payment verifyPaymentOnReceiverSide(String paymentId,String receiverId,String receiverAPIKey);
   public Payment initiatePayment(String senderPlayerId, String receiverPlayerId,Long amount);

}