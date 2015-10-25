package uk.co.cleopatra.jxlite;

public class JxLite {
	private static final UnmarshallerFactory FACTORY = new UnmarshallerFactoryImpl();
	
	public static <T> Unmarshaller<T> unmarshallerFor(Class<T> intf) {
		return FACTORY.build(intf, new NodeConverterRegistry());
	}

	public static <T> Unmarshaller<T> unmarshallerFor(Class<T> intf, NodeConverterRegistry nodeConverterRegistry) {
		return FACTORY.build(intf, nodeConverterRegistry);
	}
	
	
	public static NodeConverterRegistry getNodeConverterRegistry() {
		return new NodeConverterRegistry();
	}
}
