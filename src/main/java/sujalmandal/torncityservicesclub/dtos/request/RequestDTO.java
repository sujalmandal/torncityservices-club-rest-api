package sujalmandal.torncityservicesclub.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sujalmandal.torncityservicesclub.models.Player;

@Getter
@Setter
@ToString
public class RequestDTO {
    protected String apiKey;
    protected String fingerprint;
    protected Player player;
}