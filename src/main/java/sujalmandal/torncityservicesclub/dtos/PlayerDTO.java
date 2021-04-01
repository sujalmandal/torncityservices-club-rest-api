package sujalmandal.torncityservicesclub.dtos;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class PlayerDTO {
    private String internalId;
    private Integer tornUserId;
    private String tornUserName;
    private LocalDateTime registeredAt;
    private String activeSubscriptionType;
}
