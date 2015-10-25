package uk.co.cleopatra.jxlite;

public interface UnmarshallerFactory {
	<T> Unmarshaller<T> build(Class<T> intf, NodeConverterRegistry nodeConverterRegistry);
}
