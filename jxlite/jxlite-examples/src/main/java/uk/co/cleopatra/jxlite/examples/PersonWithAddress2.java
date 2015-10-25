package uk.co.cleopatra.jxlite.examples;

import uk.co.cleopatra.jxlite.annotations.Path;

public interface PersonWithAddress2 {
	@Path("forename")
	String getForename();

	@Path("surname")
	String getSurname();

	@Path("@age")
	int getAge();
	
    @Path("address")
    Address getAddress();
}
