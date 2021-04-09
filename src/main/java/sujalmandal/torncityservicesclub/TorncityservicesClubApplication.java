package sujalmandal.torncityservicesclub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TorncityservicesClubApplication implements CommandLineRunner {

    @Autowired
    private AppConfigurer appConfigurer;

    public static void main(String[] args) {
	SpringApplication.run(TorncityservicesClubApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
	appConfigurer.configure();
	log.info("loaded TorncityservicesClubApplication !");
    }

}