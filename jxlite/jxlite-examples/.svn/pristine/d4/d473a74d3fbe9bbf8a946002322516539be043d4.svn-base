package uk.co.cleopatra.jxlite.examples;

import java.awt.Color;

import org.w3c.dom.Attr;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.converters.NodeConverterBase;

public class ColorConverter extends NodeConverterBase {

	public ColorConverter() {
		super(Color.class);
	}

	@Override
	protected Object doConvert(Node node) throws Exception {
		Attr attr = (Attr) node;
		int rgb = Integer.parseInt(attr.getValue(), 16);
		Color color = new Color(rgb);
		return color;
	}
}
