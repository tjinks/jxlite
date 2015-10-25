package uk.co.cleopatra.jxlite.converters;

public class DoubleTypeConverter extends PrimitiveTypeConverter {

	public DoubleTypeConverter() {
		super(Double.TYPE, new DoubleClassConverter());
	}

}
