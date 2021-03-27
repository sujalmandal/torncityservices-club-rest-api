package sujalmandal.torncityservicesclub.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = -7243997614490380095L;
    
    private int statusCode;
    private String message;
    private Exception error;

    public ServiceException(Exception e){
        this.error=e;
        this.message=e.getMessage();
        this.statusCode=500;
    }
    
}