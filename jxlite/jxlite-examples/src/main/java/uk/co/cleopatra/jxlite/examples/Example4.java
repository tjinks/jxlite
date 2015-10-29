package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class Example4 {

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader
				.parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/personWithAddress.xml");
		Unmarshaller<PersonWithAddress2> unmarshaller = JxLite
				.unmarshallerFor(PersonWithAddress2.class);
		PersonWithAddress2 person = unmarshaller.unmarshal(doc);
		System.out.println(person.getForename() + " " + person.getSurname()
				+ ", " + person.getAge());
		Address address = person.getAddress();
		System.out.println(address.getLine1());
		System.out.println(address.getLine2());
		System.out.println(address.getPostcode());
	}
}
