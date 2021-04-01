package sujalmandal.torncityservicesclub.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import sujalmandal.torncityservicesclub.torn.models.TornPlayer;

@Data
@Document(collection = "Player")
@NoArgsConstructor
public class Player {
    
    @Id
    private String internalId;
    @Indexed(name = "tornUserId", unique = true, sparse = true)
    private Integer tornUserId;
    private String tornUserName;
    private LocalDateTime registeredAt;
    private String subscriberId;

    public Player(TornPlayer tornPlayerInfo){
        this.tornUserName=tornPlayerInfo.getName();
        this.tornUserId=tornPlayerInfo.getPlayerId();
    }
    
}