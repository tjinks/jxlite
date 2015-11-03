package uk.co.cleopatra.jxlite.converters;

import uk.co.cleopatra.jxlite.JxLiteClientException;

/**
 * The exception that is thrown when JxLite is unable to successfully parse
 * an xs:datetime string in the input XML.
 */
public class JxLiteDateFormatException extends JxLiteClientException {
	private static final long serialVersionUID = 1L;

	public JxLiteDateFormatException() {
		super("Invalid XML date/time format");
	}
}
