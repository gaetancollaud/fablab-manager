package net.collaud.fablab.api.exceptions;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class FablabException extends RuntimeException{

	public FablabException(String message, Throwable cause) {
		super(message, cause);
	}

	public FablabException(String message) {
		super(message);
	}
	
}
