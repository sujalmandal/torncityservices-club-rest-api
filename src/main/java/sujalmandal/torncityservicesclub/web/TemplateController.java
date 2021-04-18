package sujalmandal.torncityservicesclub.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import sujalmandal.torncityservicesclub.services.JobService;

/** public APIs **/

@RestController
@RequestMapping(
    "/templates"
)
public class TemplateController {

    @Autowired
    private JobService jobService;

    @RequestMapping(
	    method = RequestMethod.GET,
	    path = "/{templateName}"
    )
    public ResponseEntity<?> getTemplate(@PathVariable(
	"templateName"
    )
    String templateName) throws JsonProcessingException {
	return ResponseEntity.ok().body(jobService.getTemplateByTemplateName(templateName));
    }

    @RequestMapping(
	    method = RequestMethod.GET,
	    path = "/availableTemplateNames"
    )
    public ResponseEntity<?> getAvailableTemplateNames() throws JsonProcessingException {
	return ResponseEntity.ok().body(jobService.getAvailableTemplateNames());
    }
}
