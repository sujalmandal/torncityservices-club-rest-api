package sujalmandal.torncityservicesclub;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.dtos.commons.PlayerDTO;
import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.response.JobResponseDTO;
import sujalmandal.torncityservicesclub.enums.ServiceTypeValue;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;
import sujalmandal.torncityservicesclub.models.jobdetails.HospitalizeJobDetails;
import sujalmandal.torncityservicesclub.models.jobdetails.MugJobDetails;
import sujalmandal.torncityservicesclub.services.JobService;
import sujalmandal.torncityservicesclub.services.PlayerService;
import sujalmandal.torncityservicesclub.services.TornAPIService;
import sujalmandal.torncityservicesclub.torn.models.PlayerEventsDTO;
import sujalmandal.torncityservicesclub.utils.AppUtils;
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
    private JobService jobService;

    private static Player player;

    @AfterAll
    static void tearDownTests() {
	MongoTemplate mongoTemplate = StaticContextAccessor.getBean(MongoTemplate.class);
	mongoTemplate.dropCollection(Player.class);
	mongoTemplate.dropCollection(Subscription.class);
	mongoTemplate.dropCollection(Payment.class);
	mongoTemplate.dropCollection(Job.class);
	log.info("wiping test data complete!");
    }

    @Test
    @Order(1)
    public void testTornPlayerDataFetch() {
	log.info("testTornPlayerDataFetch()");
	Optional<Player> player = tornService.getPlayer(myAPIKey);
	Assert.notNull(player.get(), "player must be not null!");
	log.info("testTornPlayerDataFetch() -> player data fetched from torn {}", player);
    }

    @Test
    @Order(2)
    public void testTornPlayerEventsFetch() {
	log.info("testTornPlayerEventsFetch()");
	PlayerEventsDTO eventsDTO = tornService.getEvents(myAPIKey);
	Assert.notNull(eventsDTO, "events must be not null!");
	Collections.sort(eventsDTO.getEvents());
	log.info("testTornPlayerEventsFetch() --> total events fetched {}", eventsDTO.getEvents().size());
	log.info("sample event data -> {}", eventsDTO.getEvents().get(0));
    }

    @Test
    @Order(3)
    public void testRegisterPlayer() {
	log.info("testRegisterPlayer()");
	PlayerDTO registeredPlayer = playerService.registerPlayer(myAPIKey);
	String tornUserId = registeredPlayer.getTornUserId();
	Assert.notNull(registeredPlayer, "failed to register torn player!");
	Player fetchedPlayer = playerService.getPlayerByPlayerTornId(tornUserId);
	Assert.notNull(fetchedPlayer, "failed to fetch torn player from db!");
	player = fetchedPlayer;
	log.info("testRegisterPlayer()--> registered player fetched! {}", fetchedPlayer);
    }

    @Test
    @Order(4)
    public void testJobPosting() {
	log.info("testJobPosting()");
	log.info("using previously fetched player {}", player);
	CreateJobRequestDTO createJobRequestDTO = new CreateJobRequestDTO();
	createJobRequestDTO.setServiceType(ServiceTypeValue.OFFER);
	createJobRequestDTO.setApiKey(myAPIKey);
	HospitalizeJobDetails hospJob = new HospitalizeJobDetails();
	hospJob.setPay(2_000_000L);
	hospJob.setTargetPlayerId(player.getTornUserId());
	hospJob.setTotalHospitalizations(20);
	createJobRequestDTO.setJobDetails(hospJob.toMap());
	Job postedJob = jobService.postJob(createJobRequestDTO);
	Assert.notNull(postedJob, "failed to post a job!");
    }

    @Test
    @Order(5)
    public void testFetchingJobs() {
	postHospitalizeJob();
	postBountyJob();
	JobFilterRequestDTO filterReq = new JobFilterRequestDTO();
	filterReq.setServiceType(ServiceTypeValue.ALL);
	List<JobResponseDTO> foundJobs = jobService.getJobsByFilter(filterReq).getJobs();
	Assert.notEmpty(foundJobs, "failed to fetch jobs");
	log.info("found {} jobs {}", foundJobs.size(), foundJobs);
    }

    @Test
    @Order(1)
    public void testExactingMoneyFromEventMessage() {
	String eventMsg = "You were sent $550,000 from AwesomePlayer with the message: asbc9";
	Long amountExtracted = AppUtils.getAmountFromEvents(eventMsg);
	log.info("amount extracted {}", amountExtracted);
	Assert.isTrue(amountExtracted.equals(550_000L), "unable to extract correct amount from message!");
    }

    private void postHospitalizeJob() {
	CreateJobRequestDTO createJobRequestDTO = new CreateJobRequestDTO();
	createJobRequestDTO.setServiceType(ServiceTypeValue.OFFER);
	createJobRequestDTO.setApiKey(myAPIKey);
	HospitalizeJobDetails hospJob = new HospitalizeJobDetails();
	hospJob.setPay(2_000_000L);
	hospJob.setTargetPlayerId(player.getTornUserId());
	hospJob.setTotalHospitalizations(20);
	createJobRequestDTO.setJobDetails(hospJob.toMap());
	jobService.postJob(createJobRequestDTO);
    }

    private void postBountyJob() {
	CreateJobRequestDTO createJobRequestDTO = new CreateJobRequestDTO();
	createJobRequestDTO.setServiceType(ServiceTypeValue.REQUEST);
	createJobRequestDTO.setApiKey(myAPIKey);
	MugJobDetails mugJobDetails = new MugJobDetails();
	mugJobDetails.setPay(50_000L);
	mugJobDetails.setTargetPlayerId(player.getTornUserId());
	mugJobDetails.setTotalMugs(5);
	jobService.postJob(createJobRequestDTO);
    }

}