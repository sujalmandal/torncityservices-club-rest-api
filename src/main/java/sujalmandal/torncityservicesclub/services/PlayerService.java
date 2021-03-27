package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.models.Player;

public interface PlayerService{

    public void registerPlayer(String APIKey);
    public Player authenticateAndReturnPlayer(String APIKey);
    
}