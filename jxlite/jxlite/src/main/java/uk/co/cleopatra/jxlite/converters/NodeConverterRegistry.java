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
		if (cl.equals(Boolean.TYPE)) {
			cl = Boolean.class;
		} else if (cl.equals(Character.TYPE)) {
			cl = Character.class;
		} else if (cl.equals(Double.TYPE)) {
			cl = Double.class;
		} else if (cl.equals(Integer.TYPE)) {
			cl = Integer.class;
		}
		return registry.get(cl);
	} 
	
	private void registerDefaultConverters() {
		register(new IntegerConverter());
		register(new BooleanConverter());
		register(new CharacterConverter());
		register(new DoubleConverter());
		register(new StringConverter());
		register(new XmlNodeConverter());
		register(new CalendarConverter());
		register(new DateConverter());
	}
		
}
