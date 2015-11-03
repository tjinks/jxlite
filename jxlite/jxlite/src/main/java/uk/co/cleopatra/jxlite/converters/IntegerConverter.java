package uk.co.cleopatra.jxlite.converters;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.JxLiteClientException;

public class IntegerConverter extends NodeConverterBase {

	public IntegerConverter() {
		super(Integer.class);
	}
	
	@Override
	protected Object doConvert(Node context)
			throws Exception {
		double d = (Double) evaluate(context, XPathConstants.NUMBER);
		double result = Math.floor(d);
		if (result != d || Math.abs(result) > Integer.MAX_VALUE) {
			throw new JxLiteClientException("Invalid value for integer");
		}
		return (int) result;
	}
	

}
