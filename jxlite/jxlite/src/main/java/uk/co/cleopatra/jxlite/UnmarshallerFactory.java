package uk.co.cleopatra.jxlite;

import uk.co.cleopatra.jxlite.converters.NodeConverterRegistry;

interface UnmarshallerFactory {
	<T> Unmarshaller<T> build(Class<T> intf, NodeConverterRegistry nodeConverterRegistry);
}
