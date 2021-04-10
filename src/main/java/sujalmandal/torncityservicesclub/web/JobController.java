package sujalmandal.torncityservicesclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import sujalmandal.torncityservicesclub.dtos.request.CreateJobRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobFilterRequestDTO;
import sujalmandal.torncityservicesclub.dtos.request.JobUpateRequestDTO;
import sujalmandal.torncityservicesclub.services.JobService;

@RestController
@RequestMapping("/job")
public class JobController implements AuthenticatedController {

    @Autowired
    private JobService jobService;

    @RequestMapping(method = RequestMethod.POST, path = "/post")
    public ResponseEntity<?> postJob(@RequestBody CreateJobRequestDTO request) {
	this.injectPlayerInRequest(request);
	return ResponseEntity.ok().body(jobService.postJob(request));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/accept")
    public ResponseEntity<?> acceptJob(@RequestBody JobUpateRequestDTO request) {
	this.injectPlayerInRequest(request);
	return ResponseEntity.ok().body(jobService.acceptJob(request));
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/finish")
    public ResponseEntity<?> finishJob(@RequestBody JobUpateRequestDTO request) {
	this.injectPlayerInRequest(request);
	return ResponseEntity.ok().body(jobService.finishJob(request));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/cancel")
    public ResponseEntity<?> cancelJob(@RequestBody JobUpateRequestDTO request) {
	this.injectPlayerInRequest(request);
	return ResponseEntity.ok().body(jobService.cancelJob(request));
    }

    /** public API **/
    @RequestMapping(method = RequestMethod.POST, path = "/search")
    public ResponseEntity<?> search(@RequestBody JobFilterRequestDTO request) {
	this.injectPlayerInRequest(request);
	request.setFilterFields(null);
	return ResponseEntity.ok().body(jobService.getJobsByFilter(request));
    }

    @RequestMapping(method = RequestMethod.POST, path = "/advancedSearch")
    public ResponseEntity<?> advancedSearch(@RequestBody JobFilterRequestDTO request) {
	this.injectPlayerInRequest(request);
	return ResponseEntity.ok().body(jobService.getJobsByFilter(request));
    }

}