package sujalmandal.torncityservicesclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import sujalmandal.torncityservicesclub.services.JobService;

@RestController
@RequestMapping("/public")
public class TemplateController {

    @Autowired
    private JobService jobService;

    @RequestMapping(method = RequestMethod.GET, path = "/jobDetailFormTemplate/{templateName}")
    public ResponseEntity<?> getJobDetailFormTemplateByName(@PathVariable("templateName") String templateName)
	    throws JsonProcessingException {
	return ResponseEntity.ok().body(jobService.getJobDetailFormTemplateForTemplateName(templateName).toJson());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/jobDetailFilterTemplate/{templateName}")
    public ResponseEntity<?> getJobDetailFilterTemplateByName(@PathVariable("templateName") String templateName)
	    throws JsonProcessingException {
	return ResponseEntity.ok().body(jobService.getJobDetailFilterTemplateForTemplateName(templateName).toJson());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/jobDetailTemplates")
    public ResponseEntity<?> getAvailableJobDetailTemplateKeys() throws JsonProcessingException {
	return ResponseEntity.ok().body(jobService.getJobDetailTemplateInformation());
    }
}
