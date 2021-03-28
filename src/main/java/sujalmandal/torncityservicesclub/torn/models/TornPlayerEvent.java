package sujalmandal.torncityservicesclub.torn.models;

import java.util.HashMap;

import lombok.Data;

@Data
public class TornPlayerEvent implements Comparable<TornPlayerEvent>{
    private String id;
    private Integer timestamp;
    private String eventText;
    private Boolean seen=Boolean.FALSE;

    public TornPlayerEvent(HashMap<?,?> event){
        this.timestamp=(Integer) event.get("timestamp");
        this.eventText = (String) event.get("event");
        this.seen = ((Integer) event.get("seen")==0?Boolean.TRUE:Boolean.FALSE);
    }

    @Override
    public int compareTo(TornPlayerEvent o) {
        //latest events first
        return o.timestamp-this.timestamp;
    }
}