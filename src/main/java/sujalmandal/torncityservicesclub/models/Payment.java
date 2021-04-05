package sujalmandal.torncityservicesclub.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import sujalmandal.torncityservicesclub.enums.PaymentStatus;

@Data
@Document(collection = "Payment")
public class Payment {

    @Id
    private String id;
    private Long amount;
    private String verificationCode;
    private PaymentStatus status = PaymentStatus.INITIATED;
    private LocalDateTime paymentInitiatedDateTime;
    private LocalDateTime paymentVerifiedDateTime;
    private String fromPlayerId;
    private String toPlayerId;

}