package sujalmandal.torncityservicesclub.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;
import sujalmandal.torncityservicesclub.repositories.PlayerRepository;
import sujalmandal.torncityservicesclub.repositories.SubscriptionRepository;
import sujalmandal.torncityservicesclub.services.PlayerService;
import sujalmandal.torncityservicesclub.services.TornAPIService;

@Service
@Slf4j
public class PlayerServiceImpl implements PlayerService{

    @Autowired
    private TornAPIService tornService;
    @Autowired
    private PlayerRepository playerRepo;
    @Autowired
    private SubscriptionRepository subRepo;

    @Override
    public Player registerPlayer(String APIKey) {
        try{
            Player player = authenticateAndReturnPlayer(APIKey);
            if(player!=null){
                log.info("player data successfully fetched from torn {}",player);
                player.setRegisteredAt(LocalDateTime.now());
                Player registeredPlayer = playerRepo.save(player);
                Subscription subscription = new Subscription();
                subscription.setPlayerId(registeredPlayer.getInternalId());
                subRepo.save(subscription);
                log.info("player successfully registered {}",registeredPlayer);
                return registeredPlayer;
            }
            return null;
        }
        catch(Exception e){
            log.error("failed to register player!", e);
            throw new ServiceException(e);
        }
        
    }

    @Override
    public Player authenticateAndReturnPlayer(String APIKey) {
        try{
            return tornService.getPlayer(APIKey);
        }
        catch(Exception e){
            log.error("failed to authenticate player with torn", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Player getPlayerByPlayerTornId(Integer tornId) {
        return playerRepo.findByTornUserId(tornId);
    }
    
}