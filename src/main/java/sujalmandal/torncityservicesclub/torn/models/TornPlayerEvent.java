package sujalmandal.torncityservicesclub.torn.models;

import java.util.HashMap;

import lombok.Data;

@Data
public class TornPlayerEvent {
    private String id;
    private Integer timestamp;
    private String event;
    private Boolean seen=Boolean.FALSE;

    public TornPlayerEvent(HashMap<?,?> event){
        this.timestamp=(Integer) event.get("timestamp");
        this.event = (String) event.get("event");
        this.seen = ((Integer) event.get("seen")==0?Boolean.TRUE:Boolean.FALSE);
    }
}