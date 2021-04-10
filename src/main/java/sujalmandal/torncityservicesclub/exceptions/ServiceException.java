package sujalmandal.torncityservicesclub.exceptions;

import java.util.HashMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = -7243997614490380095L;

    private int statusCode;
    private String message;
    private Map<String, String> validationErrors;

    public ServiceException(String message, int statusCode) {
	this.message = message;
	this.statusCode = statusCode;
    }

    public ServiceException(String message, Map<String, String> validationErrors, int statusCode) {
	this.message = message;
	this.validationErrors = validationErrors;
	this.statusCode = statusCode;
    }

    public ServiceException(String message) {
	this(new RuntimeException(message));
    }

    public ServiceException(Exception e) {
	super(e);
	this.message = e.getMessage();
	this.statusCode = 500;
    }

    public Map<?, ?> toErrorMap() {
	Map<String, Object> errorMap = new HashMap<String, Object>();
	errorMap.put("statusCode", this.statusCode);
	errorMap.put("message", this.message);
	if (this.validationErrors != null) {
	    errorMap.put("validationErrors", this.validationErrors);
	}
	return errorMap;
    }

}