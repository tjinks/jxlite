package uk.co.cleopatra.jxlite.converters;

public class IntegerTypeConverter extends PrimitiveTypeConverter {
	
	public IntegerTypeConverter() {
		super(Integer.TYPE, new IntegerClassConverter());
	}

}
