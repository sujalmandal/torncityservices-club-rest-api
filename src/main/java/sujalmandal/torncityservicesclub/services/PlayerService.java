package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.models.Player;

public interface PlayerService{
    
    public Player getPlayerByPlayerTornId(Integer tornId);
    public Player registerPlayer(String APIKey);
    public Player authenticateAndReturnPlayer(String APIKey);
    
}