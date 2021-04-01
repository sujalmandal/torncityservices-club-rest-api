package sujalmandal.torncityservicesclub.services.impl;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import sujalmandal.torncityservicesclub.exceptions.InvalidAPIKeyException;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.services.TornAPIService;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.torn.models.TornPlayer;
import sujalmandal.torncityservicesclub.torn.models.TornPlayerEvent;

@Service
public class TornAPIServiceImpl implements TornAPIService {

    @Value("${torn.api.baseurl}")
    private String tornAPIBaseUrl;

    @Value("${torn.api.player.info.url}")
    private String tornPlayerInfoEndPoint;

    @Value("${torn.api.player.events.url}")
    private String tornPlayerEventsEndPoint;

    private WebClient webClient;

    @Override
    public Player getPlayer(String APIKey) {
        Player player = null;
        initWebClient();

        TornPlayer tornPlayerInfo = webClient.get().uri(String.format(tornPlayerInfoEndPoint, APIKey))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve()
                .bodyToMono(TornPlayer.class).block();

        if (tornPlayerInfo.getPlayerId()!=null) {
            player = new Player(tornPlayerInfo);
        }
        return player;
    }

    @Override
    public PlayerEventsDTO getEvents(String APIKey) {
        PlayerEventsDTO playerEventsDTO = new PlayerEventsDTO();
        initWebClient();

        HashMap<?, ?> response = webClient.get().uri(String.format(tornPlayerEventsEndPoint, APIKey))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).retrieve().bodyToMono(HashMap.class)
                .block();

        if (response != null) {
            HashMap<?, ?> events = (HashMap<?, ?>) response.get("events");
            events.forEach((k, v) -> {
                TornPlayerEvent event = new TornPlayerEvent((HashMap<?, ?>) v);
                event.setId((String) k);
                playerEventsDTO.getEvents().add(event);
            });
        }
        else{
            throw new InvalidAPIKeyException(String.format("The API KEY [%s] is invalid!", APIKey));
        }
        return playerEventsDTO;
    }

    private void initWebClient() {
        if (this.webClient == null) {
            this.webClient = WebClient.create(tornAPIBaseUrl);
        }
    }

}