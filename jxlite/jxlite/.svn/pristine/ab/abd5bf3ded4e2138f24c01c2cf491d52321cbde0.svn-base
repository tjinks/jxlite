package uk.co.cleopatra.jxlite.converters;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.Unmarshaller;

public class InterfaceConverter extends NodeConverterBase {
	private final Unmarshaller<?> unmarshaller;

	public InterfaceConverter(Class<?> objectType, Unmarshaller<?> unmarshaller) {
		super(objectType);
		this.unmarshaller = unmarshaller;
	}

	@Override
	protected Object doConvert(Node node) throws Exception {
		return unmarshaller.unmarshal((Element) node);
	}

}
