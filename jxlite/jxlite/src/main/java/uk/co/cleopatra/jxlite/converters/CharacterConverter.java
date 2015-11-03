package uk.co.cleopatra.jxlite.converters;

import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.JxLiteClientException;

/**
 * Converts the text content of an XML node (which must be a single character) 
 * to a Java Character instance.
 */
public class CharacterConverter extends NodeConverterBase {
	private final StringConverter stringConverter = new StringConverter();

	public CharacterConverter() {
		super(Character.class);
	}

	@Override
	protected Object doConvert(Node node) throws Exception {
		String s = (String) stringConverter.convert(node);
		Character result = null;
		if (s != null) {
			if (s.length() != 1) {
				throw new JxLiteClientException("Invalid value '" + s + "' for single-character data");
			}
			result = new Character(s.charAt(0));
		}
		return result;
	}

}
