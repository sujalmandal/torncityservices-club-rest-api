package sujalmandal.torncityservicesclub.services;

import sujalmandal.torncityservicesclub.dtos.commons.PlayerDTO;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;

public interface PlayerService {

    public Player getPlayerByPlayerTornId(String tornId);

    public PlayerDTO registerPlayer(String APIKey);

    public PlayerDTO authenticateAndReturnPlayer(String APIKey);

    public PlayerEventsDTO getEvents(String APIKey);

    PlayerDTO authenticateAndReturnPlayerByFingerprint(String fingerprint);

}