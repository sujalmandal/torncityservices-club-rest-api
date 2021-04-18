package sujalmandal.torncityservicesclub.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sujalmandal.torncityservicesclub.constants.PaymentStatus;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.repositories.PaymentRepository;
import sujalmandal.torncityservicesclub.services.PaymentService;
import sujalmandal.torncityservicesclub.services.PlayerService;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.utils.AppUtils;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private PlayerService playerService;

    @Override
    public Payment initiatePayment(String senderPlayerId, String receiverPlayerId, Long amount) {
	Payment payment = new Payment();
	payment.setStatus(PaymentStatus.INITIATED);
	payment.setAmount(amount);
	payment.setPaymentInitiatedDateTime(LocalDateTime.now());
	payment.setFromPlayerId(senderPlayerId);
	payment.setToPlayerId(receiverPlayerId);
	payment.setVerificationCode(AppUtils.generateUUID());
	return paymentRepository.save(payment);
    }

    @Override
    public Payment verifyPaymentOnSenderSide(String paymentId, String senderId, String senderAPIKey) {
	Optional<Payment> payment = paymentRepository.findByIdAndFromPlayerId(paymentId, senderId);
	if (payment.isPresent()) {
	    Payment verifiedPayment = payment.get();
	    PlayerEventsDTO eventsDTO = playerService.getEvents(senderAPIKey);
	    if (eventsDTO != null && eventsDTO.getEvents() != null && eventsDTO.getEvents().size() != 0) {
		String verificationCode = verifiedPayment.getVerificationCode();
		Long amountPaid = AppUtils.getAmountFromEventLogs(verificationCode, eventsDTO);
		if (amountPaid != null) {
		    verifiedPayment.setAmount(amountPaid);
		    verifiedPayment.setPaymentVerifiedDateTime(LocalDateTime.now());
		    verifiedPayment.setStatus(PaymentStatus.COMPLETED);
		} else {
		    verifiedPayment.setStatus(PaymentStatus.PENDING);
		}
		return paymentRepository.save(verifiedPayment);
	    }
	    throw new ServiceException(
		    String.format("Error accessing player {%d} logs for verification of payment!", senderId), 500);
	} else {
	    throw new ServiceException(String.format("Payment data for id {%s} not found!", paymentId), 500);
	}
    }

    @Override
    public Payment verifyPaymentOnReceiverSide(String paymentId, String receiverId, String receiverAPIKey) {
	Optional<Payment> payment = paymentRepository.findByIdAndToPlayerId(paymentId, receiverId);
	if (payment.isPresent()) {
	    Payment verifiedPayment = payment.get();
	    PlayerEventsDTO eventsDTO = playerService.getEvents(receiverAPIKey);
	    if (eventsDTO != null && eventsDTO.getEvents() != null && eventsDTO.getEvents().size() != 0) {
		String verificationCode = verifiedPayment.getVerificationCode();
		Long amountPaid = AppUtils.getAmountFromEventLogs(verificationCode, eventsDTO);
		if (amountPaid != null) {
		    verifiedPayment.setAmount(amountPaid);
		    verifiedPayment.setPaymentVerifiedDateTime(LocalDateTime.now());
		    verifiedPayment.setStatus(PaymentStatus.COMPLETED);
		} else {
		    verifiedPayment.setStatus(PaymentStatus.PENDING);
		}
		return paymentRepository.save(verifiedPayment);
	    }
	    throw new ServiceException("Error verifying payment status!", 500);
	} else {
	    throw new ServiceException(String.format("Payment data for id {%s} not found!", paymentId), 500);
	}
    }

}