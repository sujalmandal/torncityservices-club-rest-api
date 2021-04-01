package sujalmandal.torncityservicesclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.dtos.PlayerDTO;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;
import sujalmandal.torncityservicesclub.exceptions.UnRegisteredPlayerException;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.services.PlayerService;

@RestController
@RequestMapping("/player")
@Slf4j
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST,path = "/register/{APIKey}")
    public ResponseEntity<?> registerPlayer(@PathVariable("APIKey") String APIKey){
        return ResponseEntity.ok().body(playerService.registerPlayer(APIKey));
    }

    @RequestMapping(method = RequestMethod.POST,path = "/auth/{APIKey}")
    public ResponseEntity<?> authenticateAndReturnPlayer(@PathVariable("APIKey") String APIKey){
        PlayerDTO player = null;
        try{
            player = playerService.authenticateAndReturnPlayer(APIKey);
        }
        catch(UnRegisteredPlayerException e){
            log.warn("player not registered with torncityservices.club, registering now.");
            player = playerService.registerPlayer(APIKey);
        }
        return ResponseEntity.ok().body(player);
    }
}