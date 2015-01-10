package net.collaud.fablab.api.exceptions;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class FablabWrongParameterException extends FablabException{

	public FablabWrongParameterException(String message, Throwable cause) {
		super(message, cause);
	}

	public FablabWrongParameterException(String message) {
		super(message);
	}
	
}
