package uk.co.cleopatra.jxlite.examples;

import java.awt.Color;

import uk.co.cleopatra.jxlite.converters.TextContentConverter;

public class ColorConverter extends TextContentConverter {

	public ColorConverter() {
		super(Color.class);
	}

	@Override
	public Object stringToObject(String nodeValue) {
		int rgb = Integer.parseInt(nodeValue, 16);
		Color color = new Color(rgb);
		return color;
	}
}
