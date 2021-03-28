package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;

public interface PlayerService{
    
    public Player getPlayerByPlayerTornId(Integer tornId);
    public Player registerPlayer(String APIKey);
    public Player authenticateAndReturnPlayer(String APIKey);
    public PlayerEventsDTO getEvents(String APIKey);
    
}