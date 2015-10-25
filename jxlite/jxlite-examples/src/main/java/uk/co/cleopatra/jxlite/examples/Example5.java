package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class Example5 {

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader
				.parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/personWithMultilineAddress.xml");
		Unmarshaller<PersonWithMultiLineAddress> unmarshaller = JxLite
				.unmarshallerFor(PersonWithMultiLineAddress.class);
		PersonWithMultiLineAddress person = unmarshaller.unmarshal(doc);
		System.out.println(person.getForename() + " " + person.getSurname()
				+ ", " + person.getAge());
		MultiLineAddress address = person.getAddress();
		for (String line : address.getAddressLines()) {
			System.out.println(line);
		}
		System.out.println(address.getPostcode());
	}

}
