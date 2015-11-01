package uk.co.cleopatra.jxlite;

import uk.co.cleopatra.jxlite.converters.NodeConverterRegistry;

/**
 * This class provides static methods which are the main entry points into JxLite.
 */
public class JxLite {
	private static final UnmarshallerFactory FACTORY = new UnmarshallerFactoryImpl();
	
	/**
	 * Returns an unmarshaller for the specified interface type using the default set of
	 * converters.
	 * @param intf Interface type 
	 * @return An unmarshaller that can be used to unmarshal XML into an instance of the specified intreface type
	 */
	public static <T> Unmarshaller<T> unmarshallerFor(Class<T> intf) {
		return FACTORY.build(intf, new NodeConverterRegistry());
	}

	/**
	 * Returns an unmarshaller for the specified interface type using the specified set of
	 * converters.
	 * @param intf Interface type 
	 * @param nodeConverterRegistry Converters to use to when converting XML to Java types
	 * @return An unmarshaller that can be used to unmarshal XML into an instance of the specified intreface type
	 */
	public static <T> Unmarshaller<T> unmarshallerFor(Class<T> intf, NodeConverterRegistry nodeConverterRegistry) {
		return FACTORY.build(intf, nodeConverterRegistry);
	}
	
	/**
	 * Returns a copy of the default set of node converters. This set can then be modified and passed to
	 * {@link #unmarshallerFor(Class, NodeConverterRegistry)}
	 * @return Default set of node converters
	 */
	public static NodeConverterRegistry getNodeConverterRegistry() {
		return new NodeConverterRegistry();
	}
}
