package sujalmandal.torncityservicesclub.services.impl;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sujalmandal.torncityservicesclub.dtos.SubscriptionPaymentDetailsDTO;
import sujalmandal.torncityservicesclub.dtos.SubscriptionRequestDTO;
import sujalmandal.torncityservicesclub.dtos.SubscriptionVerificationRequestDTO;
import sujalmandal.torncityservicesclub.dtos.SubscriptionVerificationResponseDTO;
import sujalmandal.torncityservicesclub.enums.SubscriptionType;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;
import sujalmandal.torncityservicesclub.repositories.SubscriptionRepository;
import sujalmandal.torncityservicesclub.services.PaymentService;
import sujalmandal.torncityservicesclub.services.SubscriptionService;
import sujalmandal.torncityservicesclub.utils.AppUtils;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Value("${torn.transhumanist.id}")
    private String transhumanistId;

    @Value("${torn.transhumanist.apikey}")
    private String transhumanistAPIKey;

    @Autowired
    private PaymentService paymentService;
    @Autowired
    private SubscriptionRepository subscriptionRepo;

    private SubscriptionPaymentDetailsDTO initSubscribeForLifeTime(Subscription subscription) {
        Payment lifeTimeSubPayment = paymentService.initiatePayment(subscription.getPlayerId(), transhumanistId,
                AppUtils.getSubscriptionCost(subscription.getSubscriptionType()));
        subscription.getPaymentIds().add(lifeTimeSubPayment.getId());
        subscription.setSubscriptionType(SubscriptionType.LIFE_TIME);
        subscriptionRepo.save(subscription);
        SubscriptionPaymentDetailsDTO paymentDetailsDTO = AppUtils.getSubscriptionPaymentDetails(lifeTimeSubPayment);
        paymentDetailsDTO.setSubscriptionId(subscription.getId());
        return paymentDetailsDTO;
    }

    private SubscriptionPaymentDetailsDTO initSubscribeForAMonth(Subscription subscription) {
        Payment paymentForOneMonthTimeSub = paymentService.initiatePayment(subscription.getPlayerId(), transhumanistId,
                AppUtils.getSubscriptionCost(subscription.getSubscriptionType()));
        subscription.getPaymentIds().add(paymentForOneMonthTimeSub.getId());
        subscription.setSubscriptionType(SubscriptionType.ONE_MONTH);
        subscriptionRepo.save(subscription);
        SubscriptionPaymentDetailsDTO paymentDetailsDTO = AppUtils
                .getSubscriptionPaymentDetails(paymentForOneMonthTimeSub);
        paymentDetailsDTO.setSubscriptionId(subscription.getId());
        return paymentDetailsDTO;
    }

    @Override
    public void removeSubscription(Player player) {

    }

    @Override
    public SubscriptionPaymentDetailsDTO initiateSubscription(SubscriptionRequestDTO request) {
        Subscription subscription = subscriptionRepo.findByPlayerId(request.getPlayerId());
        switch (subscription.getSubscriptionType()) {
        case LIFE_TIME:
            return initSubscribeForLifeTime(subscription);
        case ONE_MONTH:
            return initSubscribeForAMonth(subscription);
        default:
            throw new ServiceException(
                    String.format("Unsupported subscription type {} !", subscription.getSubscriptionType()), 400);
        }
    }

    @Override
    public SubscriptionVerificationResponseDTO verifySubscription(
            SubscriptionVerificationRequestDTO verificationRequest) {

        Subscription subscription = subscriptionRepo.findByPlayerId(verificationRequest.getPlayerId());
        boolean isPaymentIdValid = subscription.getPaymentIds().stream().anyMatch(pId -> {
            return pId.equals(verificationRequest.getPaymentId());
        });
        if (isPaymentIdValid) {
            SubscriptionVerificationResponseDTO responseDTO = new SubscriptionVerificationResponseDTO();
            Payment verifiedPayment = paymentService.verifyPaymentOnReceiverSide(verificationRequest.getPaymentId(),
                    transhumanistId, transhumanistAPIKey);
            if (verifiedPayment != null) {

                responseDTO.setPaymentId(verifiedPayment.getId());
                responseDTO.setPlayerId(verificationRequest.getPlayerId());
                responseDTO.setPaymentStatus(verifiedPayment.getStatus());

                Long amountPaid = verifiedPayment.getAmount();
                if (amountPaid >= AppUtils.getSubscriptionCost(subscription.getSubscriptionType())) {
                    subscription.setSubscribedOn(LocalDate.now());
                    subscription.setIsActive(Boolean.TRUE);
                    subscriptionRepo.save(subscription);
                }
                return responseDTO;
            }
        } else {
            throw new ServiceException(String.format("Payment id {%s} not found!", verificationRequest.getPaymentId()),
                    400);
        }
        return null;
    }

}