package sujalmandal.torncityservicesclub.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import sujalmandal.torncityservicesclub.models.Player;

public interface PlayerRepository extends CrudRepository<Player, String> {

    public Player findByTornUserId(String tornUserId);

    public Optional<Player> findByFingerprint(String fingerprint);
}