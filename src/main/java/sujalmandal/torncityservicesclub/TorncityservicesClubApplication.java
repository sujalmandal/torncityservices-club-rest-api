package sujalmandal.torncityservicesclub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.java.Log;

@Log
@SpringBootApplication
public class TorncityservicesClubApplication implements CommandLineRunner{
	
	public static void main(String[] args) {
		SpringApplication.run(TorncityservicesClubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("TorncityservicesClub application booted!");
	}

}