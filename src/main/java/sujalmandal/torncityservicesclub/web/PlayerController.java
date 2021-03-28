package sujalmandal.torncityservicesclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sujalmandal.torncityservicesclub.services.PlayerService;

@RestController
@RequestMapping("/player")
public class PlayerController {
    
    @Autowired
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.POST,path = "/register/{APIKey}")
    public ResponseEntity<?> registerPlayer(@PathVariable("APIKey") String APIKey){
        return ResponseEntity.ok().body(playerService.registerPlayer(APIKey));
    }

    @RequestMapping(method = RequestMethod.POST,path = "/auth/{APIKey}")
    public ResponseEntity<?> authenticateAndReturnPlayer(@PathVariable("APIKey") String APIKey){
        return ResponseEntity.ok().body(playerService.authenticateAndReturnPlayer(APIKey));
    }
}