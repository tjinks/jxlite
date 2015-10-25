package uk.co.cleopatra.jxlite.examples;

import uk.co.cleopatra.jxlite.annotations.NamespaceList;
import uk.co.cleopatra.jxlite.annotations.Path;

@NamespaceList({"n=http://jxlite/names", "a=http://jxlite/addresses"})
public interface NameAndAddress {
	@Path("n:name/n:forename")
	String getForename();
	
	@Path("n:name/n:surname")
	String getSurname();
	
	@Path("a:address/@a:houseNumber")
	int getHouseNumber();
	
	@Path("a:address/a:postcode")
	String getPostcode();
}
