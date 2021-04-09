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
import sujalmandal.torncityservicesclub.models.JobDetailFilterTemplate;
import sujalmandal.torncityservicesclub.models.JobDetailFormTemplate;
import sujalmandal.torncityservicesclub.services.JobService;
import sujalmandal.torncityservicesclub.utils.TemplateGeneratorUtil;

@Component
@Slf4j
public class AppConfigurer implements WebMvcConfigurer {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Value("${app.cors.allowed.list}")
    private List<String> allowedCORSDomains;

    @Autowired
    private JobService jobService;

    public void configure() throws JsonProcessingException {
	updateJobDetailFormTemplates();
	updateJobDetailFilterTemplates();
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

    private void updateJobDetailFormTemplates() throws JsonProcessingException {
	mongoTemplate.dropCollection(JobDetailFormTemplate.class);
	log.info("Creating job detail form templates..");
	Set<JobDetailFormTemplate> generatedFormTemplates = TemplateGeneratorUtil.generateJobDetailFormTemplates();
	for (JobDetailFormTemplate template : generatedFormTemplates) {
	    mongoTemplate.save(template);
	}
    }

    private void updateJobDetailFilterTemplates() throws JsonProcessingException {
	mongoTemplate.dropCollection(JobDetailFilterTemplate.class);
	log.info("Creating job detail filter templates..");
	Set<JobDetailFilterTemplate> generatedFilterTemplates = TemplateGeneratorUtil
		.generateJobDetailFilterTemplates();
	for (JobDetailFilterTemplate template : generatedFilterTemplates) {
	    log.info("Saving {} filter template!", template.getFilterTemplateName());
	    mongoTemplate.save(template);
	}
    }
}
