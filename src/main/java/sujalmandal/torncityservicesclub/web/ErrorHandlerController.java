package sujalmandal.torncityservicesclub.web;

import java.util.Collections;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import lombok.extern.slf4j.Slf4j;
import sujalmandal.torncityservicesclub.exceptions.ServiceException;

@ControllerAdvice
@Slf4j
public class ErrorHandlerController {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<Object> handleCityNotFoundException(ServiceException ex, WebRequest request) {
        log.error("Exception occurred: "+ex.getMessage(), ex);
        return new ResponseEntity<>(Collections.singletonMap("errorMessage", ex.getMessage()),
                HttpStatus.valueOf(ex.getStatusCode()));
    }

}