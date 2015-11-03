package uk.co.cleopatra.jxlite.converters;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.JxLiteClientException;

public class DoubleConverter extends NodeConverterBase {

	public DoubleConverter() {
		super(Double.class);
	}

	@Override
	protected Object doConvert(Node node) throws Exception {
		Double result = (Double) evaluate(node, XPathConstants.NUMBER);
		if (Double.isNaN(result)) {
			throw new JxLiteClientException("Invalid value for double");
		}
		return result;
	}

}
