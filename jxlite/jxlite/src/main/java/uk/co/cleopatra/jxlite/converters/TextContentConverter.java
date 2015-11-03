package uk.co.cleopatra.jxlite.converters;

import org.w3c.dom.Node;


/**
 * Base class for node converters that convert the raw string value of a node's text
 * content to another Java object
 */
public abstract class TextContentConverter extends NodeConverterBase {
	
	private final StringConverter stringConverter = new StringConverter();

	protected TextContentConverter(Class<?> objectType) {
		super(objectType);
	}

	@Override
	protected final Object doConvert(Node node) throws Exception {
		String s = (String) stringConverter.convert(node);
		if (s == null) {
			return null;
		} else {
			return stringToObject(s);
		}
	}

	/**
	 * Derived classes must override this method to convert the text content of an
	 * XML node to an appropriate Java object.
	 * @param nodeValue Text to be converted
	 * @return Java object
	 */
	public abstract Object stringToObject(String nodeValue);

}
