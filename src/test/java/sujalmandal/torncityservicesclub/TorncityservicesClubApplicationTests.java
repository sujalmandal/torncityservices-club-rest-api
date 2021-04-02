package sujalmandal.torncityservicesclub;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
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
import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.PlayerDTO;
import sujalmandal.torncityservicesclub.enums.ServiceType;
import sujalmandal.torncityservicesclub.models.Job;
import sujalmandal.torncityservicesclub.models.JobDetails;
import sujalmandal.torncityservicesclub.models.Payment;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.models.Subscription;
import sujalmandal.torncityservicesclub.models.jobdetails.HospitalizeJobDetails;
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
	Player player = tornService.getPlayer(myAPIKey);
	Assert.notNull(player, "player must be not null!");
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
	createJobRequestDTO.setAmount(3);
	createJobRequestDTO.setServiceType(ServiceType.OFFERING);
	createJobRequestDTO.setListedByPlayerId(player.getInternalId());
	createJobRequestDTO.setPay(100_000_000L);
	Job postedJob = jobService.postJob(createJobRequestDTO);
	Assert.notNull(postedJob, "failed to post a job!");
    }

    @Test
    @Order(5)
    public void testFetchingJobs() {
	postHospitalizeJob();
	postBountyRevealJob();
	postMugJob();
	postBountyJob();
	JobFilterRequestDTO filterReq = new JobFilterRequestDTO();
	List<ServiceType> serviceTypes = new ArrayList<>();
	serviceTypes.add(ServiceType.OFFERING);
	serviceTypes.add(ServiceType.REQUESTING);
	filterReq.setServiceTypes(serviceTypes);
	List<Job> foundJobs = jobService.getJobsByFilter(filterReq).getJobs();
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
	createJobRequestDTO.setAmount(3);
	createJobRequestDTO.setServiceType(ServiceType.OFFERING);
	createJobRequestDTO.setListedByPlayerId(player.getInternalId());
	createJobRequestDTO.setPay(10_000_000L);
	HospitalizeJobDetails hospJob = new HospitalizeJobDetails();
	hospJob.setPay(2_000_000);
	hospJob.setTargetPlayerId(player.getTornUserId());
	hospJob.setTotalHospitalizations(20);
	createJobRequestDTO.setJobDetails(hospJob);
	jobService.postJob(createJobRequestDTO);
    }

    private void postBountyRevealJob() {
	CreateJobRequestDTO createJobRequestDTO = new CreateJobRequestDTO();
	createJobRequestDTO.setServiceType(ServiceType.REQUESTING);
	createJobRequestDTO.setListedByPlayerId(player.getInternalId());
	createJobRequestDTO.setPay(350_000L);
	jobService.postJob(createJobRequestDTO);
    }

    private void postMugJob() {
	CreateJobRequestDTO createJobRequestDTO = new CreateJobRequestDTO();
	createJobRequestDTO.setServiceType(ServiceType.OFFERING);
	createJobRequestDTO.setAmount(20);
	createJobRequestDTO.setListedByPlayerId(player.getInternalId());
	createJobRequestDTO.setPay(50_000L);
	jobService.postJob(createJobRequestDTO);
    }

    private void postBountyJob() {
	CreateJobRequestDTO createJobRequestDTO = new CreateJobRequestDTO();
	createJobRequestDTO.setServiceType(ServiceType.REQUESTING);
	createJobRequestDTO.setAmount(100);
	createJobRequestDTO.setListedByPlayerId(player.getInternalId());
	createJobRequestDTO.setPay(100_000L);
	jobService.postJob(createJobRequestDTO);
    }

}