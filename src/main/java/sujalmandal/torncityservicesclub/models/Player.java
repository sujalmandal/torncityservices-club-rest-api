package sujalmandal.torncityservicesclub.models;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import sujalmandal.torncityservicesclub.torn.models.TornPlayerInfo;

@Data
@Document(collection = "Player")
public class Player {
 
    private Integer tornUserId;
    private String tornUserName;
    private LocalDateTime registeredAt;

    public Player(TornPlayerInfo tornPlayerInfo){
        this.tornUserName=tornPlayerInfo.getName();
        this.tornUserId=tornPlayerInfo.getPlayerId();
    }
    
}