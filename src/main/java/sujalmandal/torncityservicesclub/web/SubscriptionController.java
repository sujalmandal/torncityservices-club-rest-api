package sujalmandal.torncityservicesclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sujalmandal.torncityservicesclub.dtos.request.SubscriptionRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.SubscriptionVerificationRequestDTO;
import sujalmandal.torncityservicesclub.services.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
    
    @Autowired
    private SubscriptionService subscriptionService;

    @RequestMapping(method = RequestMethod.POST,path = "/initiate")
    public ResponseEntity<?> initiateSubscription(@RequestBody SubscriptionRequestDTO request){
        return ResponseEntity.ok().body(subscriptionService.initiateSubscription(request));
    }
    
    @RequestMapping(method = RequestMethod.POST,path = "/verify")
    public ResponseEntity<?> verifySubscription(@RequestBody SubscriptionVerificationRequestDTO request){
        return ResponseEntity.ok().body(subscriptionService.verifySubscription(request));
    }
}