package uk.co.cleopatra.jxlite.converters;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Node;


public class StringConverter extends NodeConverterBase {

	public StringConverter() {
		super(String.class);
	}

	@Override
	protected Object doConvert(Node node) throws Exception {
		return evaluate(node, XPathConstants.STRING);
	}
}
