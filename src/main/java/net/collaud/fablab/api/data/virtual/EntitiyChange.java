package net.collaud.fablab.api.data.virtual;

/**
 *
 * @author gaetan
 */
public class EntitiyChange {

	private final String field;
	private final Object oldValue;
	private final Object newValue;

	public EntitiyChange(String field, Object oldValue, Object newValue) {
		this.field = field;
		this.oldValue = oldValue;
		this.newValue = newValue;
	}

	public String getField() {
		return field;
	}

	public Object getOldValue() {
		return oldValue;
	}

	public Object getNewValue() {
		return newValue;
	}

}
