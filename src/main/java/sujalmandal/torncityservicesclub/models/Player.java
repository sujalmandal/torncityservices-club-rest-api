package sujalmandal.torncityservicesclub.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;
import sujalmandal.torncityservicesclub.enums.SubscriptionType;
import sujalmandal.torncityservicesclub.torn.models.TornPlayer;

@Data
@Document(collection = "Player")
@NoArgsConstructor
public class Player {

    @Id
    private String internalId;
    @Indexed(name = "tornUserId", unique = true, sparse = true)
    private String tornUserId;
    private String tornUserName;
    private LocalDateTime registeredAt;
    private String subscriberId;
    private SubscriptionType activeSubscriptionType;

    public Player(TornPlayer tornPlayerInfo) {
	this.tornUserName = tornPlayerInfo.getName();
	this.tornUserId = Integer.toString(tornPlayerInfo.getPlayerId());
    }

}