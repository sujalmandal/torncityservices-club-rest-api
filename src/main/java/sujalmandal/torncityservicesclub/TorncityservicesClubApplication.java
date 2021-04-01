package sujalmandal.torncityservicesclub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class TorncityservicesClubApplication implements CommandLineRunner,WebMvcConfigurer  {
	
	public static void main(String[] args) {
		SpringApplication.run(TorncityservicesClubApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("loaded TorncityservicesClubApplication !");
	}

	@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedMethods("*");
    }

}