package uk.co.cleopatra.jxlite;

import java.util.HashMap;

import uk.co.cleopatra.jxlite.converters.BooleanClassConverter;
import uk.co.cleopatra.jxlite.converters.BooleanTypeConverter;
import uk.co.cleopatra.jxlite.converters.CharacterClassConverter;
import uk.co.cleopatra.jxlite.converters.CharacterTypeConverter;
import uk.co.cleopatra.jxlite.converters.DoubleClassConverter;
import uk.co.cleopatra.jxlite.converters.DoubleTypeConverter;
import uk.co.cleopatra.jxlite.converters.IntegerClassConverter;
import uk.co.cleopatra.jxlite.converters.IntegerTypeConverter;
import uk.co.cleopatra.jxlite.converters.NodeConverter;
import uk.co.cleopatra.jxlite.converters.StringConverter;
import uk.co.cleopatra.jxlite.converters.XmlNodeConverter;
import uk.co.cleopatra.jxlite.converters.datetime.CalendarConverter;
import uk.co.cleopatra.jxlite.converters.datetime.DateConverter;

public class NodeConverterRegistry {
	private final HashMap<Class<?>, NodeConverter> registry;
	
	NodeConverterRegistry() {
		super();
		registry = new HashMap<Class<?>, NodeConverter>();
		registerDefaultConverters();
	}
	
	public NodeConverter register(NodeConverter nodeConverter) {
		return registry.put(nodeConverter.getObjectType(), nodeConverter);
	}
	
	public NodeConverter lookup(Class<?> cl) {
		return registry.get(cl);
	} 
	
	private void registerDefaultConverters() {
		register(new IntegerClassConverter());
		register(new IntegerTypeConverter());
		register(new BooleanClassConverter());
		register(new BooleanTypeConverter());
		register(new CharacterClassConverter());
		register(new CharacterTypeConverter());
		register(new DoubleClassConverter());
		register(new DoubleTypeConverter());
		register(new StringConverter());
		register(new XmlNodeConverter());
		register(new CalendarConverter());
		register(new DateConverter());
	}
		
}
