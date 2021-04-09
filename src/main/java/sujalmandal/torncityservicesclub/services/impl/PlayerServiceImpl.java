package sujalmandal.torncityservicesclub.services.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.dtos.commons.PlayerDTO;
import sujalmandal.torncityservicesclub.exceptions.InvalidAPIKeyException;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.exceptions.UnRegisteredPlayerException;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;
import sujalmandal.torncityservicesclub.repositories.PlayerRepository;
import sujalmandal.torncityservicesclub.repositories.SubscriptionRepository;
import sujalmandal.torncityservicesclub.services.PlayerService;
import sujalmandal.torncityservicesclub.services.TornAPIService;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.utils.FingerprintUtil;
import sujalmandal.torncityservicesclub.utils.PojoUtils;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private TornAPIService tornService;
    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private SubscriptionRepository subRepo;

    @Override
    public PlayerDTO registerPlayer(String APIKey) {
	try {
	    Optional<Player> fetchedFromTorn = tornService.getPlayer(APIKey);
	    if (fetchedFromTorn.isPresent()) {
		log.info("player data successfully fetched from torn {}", fetchedFromTorn);
		fetchedFromTorn.get().setRegisteredAt(LocalDateTime.now());
		fetchedFromTorn.get().setFingerprint(FingerprintUtil.getFingerprintForAPIKey(APIKey));
		Player registeredPlayer = playerRepo.save(fetchedFromTorn.get());
		Subscription subscription = new Subscription();
		subscription.setPlayerId(registeredPlayer.getInternalId());
		subRepo.save(subscription);
		registeredPlayer.setSubscriberId(subscription.getId());
		registeredPlayer.setActiveSubscriptionType(subscription.getSubscriptionType());
		log.info("player successfully registered {}", registeredPlayer);
		registeredPlayer = playerRepo.save(registeredPlayer);
		PlayerDTO playerDTO = new PlayerDTO();
		PojoUtils.map(registeredPlayer, playerDTO);
		playerDTO.setActiveSubscriptionType(subscription.getSubscriptionType().toString());
		return playerDTO;
	    }
	    throw new InvalidAPIKeyException(String.format("The API KEY [%s] is invalid!", APIKey));
	} catch (Exception e) {
	    log.error("failed to register player!", e);
	    throw new ServiceException(e);
	}
    }

    @Override
    public PlayerDTO authenticateAndReturnPlayer(String APIKey) {
	Optional<Player> fetchedFromTorn = tornService.getPlayer(APIKey);
	if (fetchedFromTorn.isPresent()) {
	    Player fetchedPlayerDb = this.getPlayerByPlayerTornId(fetchedFromTorn.get().getTornUserId());
	    if (fetchedPlayerDb != null && fetchedPlayerDb.getInternalId() != null) {
		PlayerDTO playerDTO = new PlayerDTO();
		PojoUtils.map(fetchedPlayerDb, playerDTO);
		return playerDTO;
	    } else {
		throw new UnRegisteredPlayerException(
			String.format("User %s is not registered!", fetchedFromTorn.get().getTornUserName()), 404);
	    }
	} else {
	    throw new InvalidAPIKeyException(String.format("The API KEY [%s] is invalid!", APIKey));
	}
    }

    @Override
    public PlayerDTO authenticateAndReturnPlayerByFingerprint(String fingerprint) {

	Optional<Player> fetchedPlayerDb = playerRepo.findByFingerprint(fingerprint);
	if (fetchedPlayerDb.isPresent()) {
	    PlayerDTO playerDTO = new PlayerDTO();
	    PojoUtils.map(fetchedPlayerDb.get(), playerDTO);
	    return playerDTO;
	} else {
	    throw new UnRegisteredPlayerException("User %s is not registered!", 404);
	}

    }

    @Override
    public Player getPlayerByPlayerTornId(String tornId) {
	return playerRepo.findByTornUserId(tornId);
    }

    @Override
    public PlayerEventsDTO getEvents(String APIKey) {
	PlayerEventsDTO events = tornService.getEvents(APIKey);
	return events;
    }

}