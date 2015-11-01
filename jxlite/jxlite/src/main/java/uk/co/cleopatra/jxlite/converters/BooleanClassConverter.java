package uk.co.cleopatra.jxlite.converters;

import uk.co.cleopatra.jxlite.JxLiteClientException;

/**
 * Converts the text content of an XML node to a Java Boolean instance.
 */
public class BooleanClassConverter extends TextContentConverter {

	public BooleanClassConverter() {
		super(Boolean.class);
	}

	@Override
	public Object stringToObject(String nodeValue) {
		nodeValue = nodeValue.toLowerCase();
		if (nodeValue.equals("true") || nodeValue.equals("yes")) {
			return true;
		}
		if (nodeValue.equals("false") || nodeValue.equals("no")) {
			return false;
		}
		throw new JxLiteClientException("'" + nodeValue + "' is not a valid boolean value");
	}

}
