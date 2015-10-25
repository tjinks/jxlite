package uk.co.cleopatra.jxlite.converters;

import org.w3c.dom.Node;

/**
 * A node converter that 'converts' an XML node to itself.
 */
public class XmlNodeConverter extends NodeConverterBase {

	public XmlNodeConverter() {
		super(Node.class);
	}

	/**
	 * Simply returns its argument unchanged.
	 * 
	 * @return Node
	 */
	@Override
	protected Object doConvert(Node node) throws Exception {
		return node;
	}

}
