package sujalmandal.torncityservicesclub.torn.models;

import lombok.Data;

@Data
public class TornPlayerEvent {
    private String id;
    private String timestamp;
    private String event;
    private Boolean seen=Boolean.FALSE;
}