package sujalmandal.torncityservicesclub;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.models.ServiceDetailTemplate;
import sujalmandal.torncityservicesclub.models.Template;
import sujalmandal.torncityservicesclub.services.JobService;
import sujalmandal.torncityservicesclub.utils.TemplateUtil;

@Component
@Slf4j
public class AppConfigurer implements WebMvcConfigurer {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value(
	"${app.cors.allowed.list}"
    )
    private List<String> allowedCORSDomains;

    @Autowired
    private JobService jobService;

    public void configure() throws JsonProcessingException {
	updateServiceDetailTemplates();
	jobService.initSequenceIdsIfNotPresent();
    }

    /* disable cors for selected domains */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
	allowedCORSDomains.forEach(domain -> {
	    log.warn("**** CORS is deactivated for requests coming from {} ****", domain);
	    registry.addMapping(domain).allowedMethods("*");
	});
    }

    /*
     * inject mongo transaction manager which will handle the operation of
     * spring's @Transactional
     */
    @Bean
    MongoTransactionManager injectMongoTransactionManager(MongoDatabaseFactory dbFactory) {
	return new MongoTransactionManager(dbFactory);
    }

    private void updateServiceDetailTemplates() throws JsonProcessingException {
	mongoTemplate.dropCollection(ServiceDetailTemplate.class);
	log.info("Creating service detail form templates..");
	Set<Template> generatedFormTemplates = TemplateUtil.getTemplatesFromClasses();
	for (Template template : generatedFormTemplates) {
	    mongoTemplate.save(template);
	}
    }

}
