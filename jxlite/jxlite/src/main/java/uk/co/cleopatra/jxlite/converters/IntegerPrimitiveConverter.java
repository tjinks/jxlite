package uk.co.cleopatra.jxlite.converters;

public class IntegerPrimitiveConverter extends IntegerConverter {
	@Override
	public Class<?> getObjectType() {
		return Integer.TYPE;
	}
}
