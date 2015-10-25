package uk.co.cleopatra.jxlite;

public class ObjectMethodsBase<T> {
	protected T realObject;

	@Override
	public boolean equals(Object obj) {
		return obj == realObject;
	}
}
