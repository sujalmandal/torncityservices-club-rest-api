package sujalmandal.torncityservicesclub.services;

import java.util.Optional;

import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;

public interface TornAPIService {

    public Optional<Player> getPlayer(String APIKey);

    public PlayerEventsDTO getEvents(String APIKey);

}