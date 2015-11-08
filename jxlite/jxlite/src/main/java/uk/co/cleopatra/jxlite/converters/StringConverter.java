package uk.co.cleopatra.jxlite.converters;

import org.w3c.dom.Node;

public class StringConverter extends NodeConverterBase {

	public StringConverter() {
		super(String.class);
	}

	@Override
	protected Object doConvert(Node node) throws Exception {
		return node.getTextContent();
	}
}
