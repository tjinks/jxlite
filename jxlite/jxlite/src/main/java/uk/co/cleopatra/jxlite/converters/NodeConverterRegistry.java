package uk.co.cleopatra.jxlite.converters;

import java.util.HashMap;

/**
 * An instance of this class holds a set of NodeConverters that can be used to unmarshal
 * XML into Java interface instances.
 * 
 *  @see uk.co.cleopatra.jxlite.converters.NodeConverter
 */
public class NodeConverterRegistry {
	private final HashMap<Class<?>, NodeConverter> registry;
	
	public NodeConverterRegistry() {
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
		register(new IntegerConverter());
		register(new IntegerPrimitiveConverter());
		register(new BooleanConverter());
		register(new BooleanPrimitiveConverter());
		register(new CharacterConverter());
		register(new CharacterPrimitiveConverter());
		register(new DoubleConverter());
		register(new DoublePrimitiveConverter());
		register(new StringConverter());
		register(new XmlNodeConverter());
		register(new CalendarConverter());
		register(new DateConverter());
	}
		
}
