package net.collaud.fablab.api.rest.v1.model;

/**
 *
 * @author Gaetan Collaud <gaetancollaud@gmail.com> Collaud <gaetancollaud@gmail.com>
 * @param <T>
 */
public class DataModel<T> extends BaseModel{
	
	private T data;

	public DataModel() {
	}

	public DataModel(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
