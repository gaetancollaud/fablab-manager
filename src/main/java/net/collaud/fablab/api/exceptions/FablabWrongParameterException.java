package net.collaud.fablab.api.exceptions;

/**
 *
 * @author gaetan
 */
public class FablabWrongParameterException extends FablabException{

	public FablabWrongParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public FablabWrongParameterException(String message) {
		super(message);
	}
	
}
