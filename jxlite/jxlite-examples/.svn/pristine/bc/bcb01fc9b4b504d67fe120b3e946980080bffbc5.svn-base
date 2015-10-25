package uk.co.cleopatra.jxlite.examples;

import java.awt.Color;

import uk.co.cleopatra.jxlite.annotations.ConvertUsing;
import uk.co.cleopatra.jxlite.annotations.Path;

public interface TextOutput {
	@Path("@font")
	String getFont();
	
	@Path("@size")
	int getSize();
	
	@Path("@color")
	@ConvertUsing(ColorConverter.class)
	Color getColor();
	
	@Path("text")
	String getText();
}
