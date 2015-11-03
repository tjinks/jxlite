package uk.co.cleopatra.jxlite.converters;

import uk.co.cleopatra.jxlite.JxLiteClientException;

/**
 * Converts the text content of an XML node to a Java Boolean instance.
 */
public class BooleanConverter extends TextContentConverter {

	public BooleanConverter() {
		super(Boolean.class);
	}

	/**
	 * Converts the text content of an XML node to a Java Boolean
	 * @param nodeValue Node text
	 * @return True if nodeValue is 'true' or 'yes', false if it is 'false' or 'no'
	 * @throws JxLiteClientException if nodeValue is not one of the above options
	 */
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
