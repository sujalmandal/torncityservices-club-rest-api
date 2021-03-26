package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;


public interface TornAPIService {

    public Player getPlayer(String APIKey);
    public PlayerEventsDTO getEvents(String APIKey);
    
}