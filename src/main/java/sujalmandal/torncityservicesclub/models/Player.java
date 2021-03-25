package sujalmandal.torncityservicesclub.models;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Player")
public class Player {
 
    private String tornUserId;
    private String tornUserName;
    private String tornAge;
    private LocalDateTime registeredAt;
    
}