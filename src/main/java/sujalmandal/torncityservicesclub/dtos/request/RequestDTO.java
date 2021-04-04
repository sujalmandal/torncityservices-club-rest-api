package sujalmandal.torncityservicesclub.dtos.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RequestDTO {
    private String apiKey;
    private String fingerprint;
}