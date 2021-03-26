package sujalmandal.torncityservicesclub.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.services.TornAPIService;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.torn.models.TornPlayerInfo;

@Service
public class TornAPIServiceImpl implements TornAPIService{

    @Value("${torn.api.baseurl}")
    private String tornAPIBaseUrl;

    @Value("${torn.api.player.info.url}")
    private String tornPlayerInfoEndPoint;

    private WebClient webClient;

    @Override
    public Player getPlayer(String APIKey) {
        initWebClient();

        TornPlayerInfo tornPlayerInfo = webClient.get()
        .uri(String.format(tornPlayerInfoEndPoint, APIKey))
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .retrieve()
        .bodyToMono(TornPlayerInfo.class).block();

        Player player=null;

        if(tornPlayerInfo!=null){
            player = new Player(tornPlayerInfo);
        }

        return player;
    }

    @Override
    public PlayerEventsDTO getNotifications(String APIKey) {
        initWebClient();
        
        return null;
    }

    private void initWebClient(){
        if(this.webClient==null){
            this.webClient=WebClient.create(tornAPIBaseUrl);
        }
    }
    
}