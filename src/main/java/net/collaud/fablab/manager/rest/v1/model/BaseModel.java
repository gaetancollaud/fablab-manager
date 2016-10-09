package net.collaud.fablab.manager.rest.v1.model;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com>
 */
public class BaseModel {
	private String message;
	private boolean success = true;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
}
