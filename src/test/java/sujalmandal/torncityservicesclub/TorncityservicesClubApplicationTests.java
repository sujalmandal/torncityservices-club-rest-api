package sujalmandal.torncityservicesclub;

import java.util.Collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.enums.JobType;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;
import sujalmandal.torncityservicesclub.services.JobService;
import sujalmandal.torncityservicesclub.services.PlayerService;
import sujalmandal.torncityservicesclub.services.TornAPIService;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.utils.StaticContextAccessor;

@SpringBootTest
@ActiveProfiles("test")
@Slf4j
@TestMethodOrder(OrderAnnotation.class)
class TorncityservicesClubApplicationTests {

	@Value("${torn.transhumanist.apikey}")
	private String myAPIKey;

	@Autowired
	private TornAPIService tornService;
	@Autowired
	private PlayerService playerService;
	@Autowired
	JobService jobService;

	private static Player player;

	@AfterAll
	static void tearDownTests(){
		MongoTemplate mongoTemplate = StaticContextAccessor.getBean(MongoTemplate.class);
		mongoTemplate.dropCollection(Player.class);
		mongoTemplate.dropCollection(Subscription.class);
		log.info("wiping test data complete!");
	}

	@Test
	@Order(1)
	public void testTornPlayerDataFetch(){
		log.info("testTornPlayerDataFetch()");
		Player player = tornService.getPlayer(myAPIKey);
		Assert.notNull(player, "player must be not null!");
		log.info("testTornPlayerDataFetch() -> player data fetched from torn {}", player);
	}

	@Test
	@Order(2)
	public void testTornPlayerEventsFetch(){
		log.info("testTornPlayerEventsFetch()");
		PlayerEventsDTO eventsDTO = tornService.getEvents(myAPIKey);
		Assert.notNull(eventsDTO, "events must be not null!");
		Collections.sort(eventsDTO.getEvents());
		log.info("testTornPlayerEventsFetch() --> total events fetched {}",eventsDTO.getEvents().size());
		/*
		eventsDTO.getEvents().forEach(event->{
			log.info("event-> {}",event.getId());
		});
		*/
	}

	@Test
	@Order(3)
	public void testRegisterPlayer(){
		log.info("testRegisterPlayer()");
		Player registeredPlayer = playerService.registerPlayer(myAPIKey);
		Integer tornUserId = registeredPlayer.getTornUserId();
		Assert.notNull(registeredPlayer, "failed to register torn player!");
		Player fetchedPlayer = playerService.getPlayerByPlayerTornId(tornUserId);
		Assert.notNull(fetchedPlayer, "failed to fetch torn player from db!");
		player=fetchedPlayer;
		log.info("testRegisterPlayer()--> registered player fetched! {}",fetchedPlayer);
	}

	@Test
	@Order(4)
	public void testJobPosting(){
		log.info("testJobPosting()");
		log.info("using previously fetched player {}",player);
		CreateJobRequestDTO createJobRequestDTO = new CreateJobRequestDTO();
		createJobRequestDTO.setAmount(3);
		createJobRequestDTO.setJobType(JobType.FIGHT_HOSPITALIZE);
		createJobRequestDTO.setPlayerId(player.getInternalId());
		createJobRequestDTO.setPay(100_000_000L);
		Job postedJob = jobService.postJob(createJobRequestDTO);
		Assert.notNull(postedJob, "failed to post a job!");
	}

	@Test
	@Order(5)
	public void testFetchingJobs(){

	}

}