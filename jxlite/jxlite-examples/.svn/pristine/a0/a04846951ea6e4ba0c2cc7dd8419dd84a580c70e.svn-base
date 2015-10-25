package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class Example3 {

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader
				.parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/personWithAddress.xml");
		Unmarshaller<PersonWithAddress1> unmarshaller = JxLite
				.unmarshallerFor(PersonWithAddress1.class);
		PersonWithAddress1 person = unmarshaller.unmarshal(doc);
		System.out.println(person.getForename() + " " + person.getSurname()
				+ ", " + person.getAge());
		System.out.println(person.getAddressLine1());
		System.out.println(person.getAddressLine2());
		System.out.println(person.getPostcode());
	}

}
