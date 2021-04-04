package sujalmandal.torncityservicesclub.dtos.commons;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PlayerDTO {
    private String internalId;
    private String tornUserId;
    private String tornUserName;
    private LocalDateTime registeredAt;
    private String activeSubscriptionType;
    private String fingerPrint;
}
