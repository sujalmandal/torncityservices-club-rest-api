package sujalmandal.torncityservicesclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.dtos.commons.PlayerDTO;
import sujalmandal.torncityservicesclub.exceptions.UnRegisteredPlayerException;
import sujalmandal.torncityservicesclub.services.PlayerService;

@RestController
@RequestMapping("/player")
@Slf4j
public class PlayerController {

    @Autowired
    private PlayerService playerService;

    /** public API **/

    @RequestMapping(method = RequestMethod.POST, path = "/auth/{APIKey}")
    public ResponseEntity<?> authenticateAndReturnPlayer(@PathVariable("APIKey") String APIKey) {
	PlayerDTO player = null;
	try {
	    player = playerService.authenticateAndReturnPlayer(APIKey);
	} catch (UnRegisteredPlayerException e) {
	    log.warn("player not registered with torncityservices.club, registering now.");
	    player = playerService.registerPlayer(APIKey);
	}
	return ResponseEntity.ok().body(player);
    }

    public ResponseEntity<?> getJobsAcceptedByPlayer() {
	return null;
    }

    public ResponseEntity<?> getJobsFinishedByPlayer() {
	return null;
    }

    public ResponseEntity<?> getJobsPostedByPlayer() {
	return null;
    }

    public ResponseEntity<?> getJobsPostedByPlayerAndAcceptedByOthers() {
	return null;
    }

    public ResponseEntity<?> getJobsPostedByPlayerAndFinishedByOthers() {
	return null;
    }

}