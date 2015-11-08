package uk.co.cleopatra.jxlite;

public class ObjectMethodsBase<T> {
	protected T target;

	@Override
	public boolean equals(Object obj) {
		return target.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return target.hashCode();
	}
	
	@Override
	public String toString() {
		return target.toString();
	}
}
