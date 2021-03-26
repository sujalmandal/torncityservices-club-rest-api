package sujalmandal.torncityservicesclub.torn.models;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class PlayerEventsDTO {
    List<TornPlayerEvent> events= new ArrayList<>();
}