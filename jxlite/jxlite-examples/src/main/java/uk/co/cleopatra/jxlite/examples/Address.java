package uk.co.cleopatra.jxlite.examples;

import uk.co.cleopatra.jxlite.annotations.Path;

public interface Address {
	@Path("line1")
	String getLine1();
	
	@Path("line2")
	String getLine2();
	
	@Path("postcode")
	String getPostcode();
}
