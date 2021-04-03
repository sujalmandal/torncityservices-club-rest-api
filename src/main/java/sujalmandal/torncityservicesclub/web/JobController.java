package sujalmandal.torncityservicesclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import sujalmandal.torncityservicesclub.dtos.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobAcceptRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobCancelRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.JobFinishRequestDTO;
import sujalmandal.torncityservicesclub.services.JobService;

@RestController
@RequestMapping("/job")
public class JobController {

    @Autowired
    private JobService jobService;

    @RequestMapping(method = RequestMethod.POST, path = "/post")
    public ResponseEntity<?> postJob(@RequestBody CreateJobRequestDTO request) {
	return ResponseEntity.ok().body(jobService.postJob(request));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/accept")
    public ResponseEntity<?> acceptJob(@RequestBody JobAcceptRequestDTO request) {
	return ResponseEntity.ok().body(jobService.acceptJob(request));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/finish")
    public ResponseEntity<?> finishJob(@RequestBody JobFinishRequestDTO request) {
	return ResponseEntity.ok().body(jobService.finishJob(request));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/cancel")
    public ResponseEntity<?> cancelJob(@RequestBody JobCancelRequestDTO request) {
	return ResponseEntity.ok().body(jobService.cancelJob(request));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/search")
    public ResponseEntity<?> cancelJob(@RequestBody JobFilterRequestDTO request) {
	return ResponseEntity.ok().body(jobService.getJobsByFilter(request));
    }

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
	return ResponseEntity.ok().body(jobService.getJobDetailTemplateInforamation());
    }

}