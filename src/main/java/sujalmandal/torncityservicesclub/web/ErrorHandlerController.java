package sujalmandal.torncityservicesclub.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;

@ControllerAdvice
@Slf4j
public class ErrorHandlerController {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleCityNotFoundException(ServiceException ex, WebRequest request)
	    throws JsonProcessingException {
	log.error("Exception occurred: " + ex.getMessage());
	return new ResponseEntity<>(ex.toErrorMap(), HttpStatus.valueOf(ex.getStatusCode()));
    }

}