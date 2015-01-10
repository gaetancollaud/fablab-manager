package net.collaud.fablab.api.exceptions;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class FablabConstraintException extends FablabException{
	
	public enum Constraints{
		USER_LOGIN_UNIQUE,
		USER_EMAIL_UNIQUE,
	}
	
	private final Constraints constraint;

	public FablabConstraintException(Constraints constraint) {
		super("Constraint violation : "+constraint);
		this.constraint = constraint;
	}

	public Constraints getConstraint() {
		return constraint;
	}
	
}
