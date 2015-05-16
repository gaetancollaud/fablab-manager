package net.collaud.fablab.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
@ResponseStatus(value=HttpStatus.FORBIDDEN, reason="Not permitted")
public class FablabSecurityException extends FablabException{

	public FablabSecurityException(String message) {
		super(message);
	}
	
}
