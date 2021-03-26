package sujalmandal.torncityservicesclub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.models.Player;
import sujalmandal.torncityservicesclub.services.TornAPIService;

@SpringBootTest
@Slf4j
class TorncityservicesClubApplicationTests {

	@Value("${torn.transhumanist.apikey}")
	private String myAPIKey;

	@Autowired
	TornAPIService tornService;

	@Test
	void contextLoads() {
	}

	@Test
	public void voidTestTornPlayerDataFetch(){
		log.info("Using APIKey {} to test fetching data from torn!",myAPIKey);
		Player player = tornService.getPlayer(myAPIKey);
		Assert.notNull(player, "player must be not null!");
		log.info("player data fetched from torn {}", player);
	}

}