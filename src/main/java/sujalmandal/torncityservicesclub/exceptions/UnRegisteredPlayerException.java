package sujalmandal.torncityservicesclub.exceptions;

public class UnRegisteredPlayerException extends ServiceException {
    private static final long serialVersionUID = -2182916710723978670L;

    public UnRegisteredPlayerException(String message, int statusCode) {
	super(message, statusCode);
    }

}