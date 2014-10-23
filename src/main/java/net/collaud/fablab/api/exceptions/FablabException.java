package net.collaud.fablab.api.exceptions;

/**
 *
 * @author gaetan
 */
public class FablabException extends Exception{

	public FablabException(String message, Throwable cause) {
		super(message, cause);
	}

	public FablabException(String message) {
		super(message);
	}
	
}
