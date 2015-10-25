package uk.co.cleopatra.jxlite.examples;

import java.awt.Color;

import uk.co.cleopatra.jxlite.annotations.Path;

public interface TextOutputList {
	@Path("defaultColor")
	Color getDefaultColor();
	
	@Path("output")
	TextOutput[] getOutputs();
}
