package sujalmandal.torncityservicesclub;

import java.util.Collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;
import sujalmandal.torncityservicesclub.services.PlayerService;
import sujalmandal.torncityservicesclub.services.TornAPIService;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.utils.StaticContextAccessor;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
class TorncityservicesClubApplicationTests {

	@Value("${torn.transhumanist.apikey}")
	private String myAPIKey;

	@Autowired
	TornAPIService tornService;
	@Autowired
	PlayerService playerService;

	@Test
	void contextLoads() {
		log.info("Using APIKey {} to test fetching data from torn!",myAPIKey);
	}

	@AfterAll
	static void tearDownTests(){
		MongoTemplate mongoTemplate = StaticContextAccessor.getBean(MongoTemplate.class);
		mongoTemplate.dropCollection(Player.class);
		mongoTemplate.dropCollection(Subscription.class);
		log.info("wiping test data complete!");
	}

	@Test
	public void voidTestTornPlayerDataFetch(){
		Player player = tornService.getPlayer(myAPIKey);
		Assert.notNull(player, "player must be not null!");
		log.info("player data fetched from torn {}", player);
	}

	@Test
	public void voidTestTornPlayerEventsFetch(){
		PlayerEventsDTO eventsDTO = tornService.getEvents(myAPIKey);
		Assert.notNull(eventsDTO, "events must be not null!");
		Collections.sort(eventsDTO.getEvents());
		eventsDTO.getEvents().forEach(event->{
			log.info("event-> {}",event.getId());
		});
	}

	@Test
	public void testRegisterPlayer(){
		Player registeredPlayer = playerService.registerPlayer(myAPIKey);
		Integer tornUserId = registeredPlayer.getTornUserId();
		Assert.notNull(registeredPlayer, "failed to register torn player!");
		Player fetchedPlayer = playerService.getPlayerByPlayerTornId(tornUserId);
		Assert.notNull(fetchedPlayer, "failed to fetch torn player from db!");
		log.info("registered player fetched! {}",fetchedPlayer);
	}

}