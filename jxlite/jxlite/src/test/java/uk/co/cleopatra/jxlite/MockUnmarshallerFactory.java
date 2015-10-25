package uk.co.cleopatra.jxlite;

import uk.co.cleopatra.jxlite.NodeConverterRegistry;
import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.UnmarshallerFactory;

public class MockUnmarshallerFactory implements UnmarshallerFactory {

	@SuppressWarnings("unchecked")
	@Override
	public <T> Unmarshaller<T> build(Class<T> intf, NodeConverterRegistry nodeConverterRegistry) {
		if (intf.equals(MockUnmarshaller.Intf.class)) {
			return (Unmarshaller<T>) new MockUnmarshaller();
		}
		throw new IllegalArgumentException("Unexpected interface type");
	}

}
