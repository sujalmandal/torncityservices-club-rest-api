package sujalmandal.torncityservicesclub.repositories;

import org.springframework.data.repository.CrudRepository;

import sujalmandal.torncityservicesclub.models.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {
    public Player findByTornUserId(String tornUserId);

    public Player findByFingerPrint(String fingerPrint);
}