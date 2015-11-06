package uk.co.cleopatra.jxlite.converters;

public class BooleanPrimitiveConverter extends BooleanConverter {
	@Override
	public Class<?> getObjectType() {
		return Boolean.TYPE;
	}
}
