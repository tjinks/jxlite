package uk.co.cleopatra.jxlite.examples;

import java.awt.Color;

import javax.xml.xpath.XPathConstants;

import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.converters.NodeConverterBase;

public class ColorConverter2 extends NodeConverterBase {

	public ColorConverter2() {
		super(Color.class);
	}

	@Override
	protected Object doConvert(Node node) throws Exception {
		String hexDigits = (String) evaluate(node, XPathConstants.STRING);
		int rgb = Integer.parseInt(hexDigits, 16);
		Color color = new Color(rgb);
		return color;
	}
}
