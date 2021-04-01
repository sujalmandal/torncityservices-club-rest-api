package sujalmandal.torncityservicesclub.exceptions;


public class InvalidAPIKeyException extends ServiceException {
    private static final long serialVersionUID = -2182916710723978670L;

    public InvalidAPIKeyException(String message) {
        super(message, 400);
    }

}