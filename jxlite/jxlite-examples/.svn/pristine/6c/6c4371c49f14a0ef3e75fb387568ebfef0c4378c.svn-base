package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class Example6 {

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader
				.parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/nameAndAddressWithNamespaces.xml");
		Unmarshaller<NameAndAddress> unmarshaller = JxLite
				.unmarshallerFor(NameAndAddress.class);
		NameAndAddress nameAndAddress = unmarshaller.unmarshal(doc);
		System.out.println(nameAndAddress.getForename());
		System.out.println(nameAndAddress.getSurname());
		System.out.println(nameAndAddress.getHouseNumber());
		System.out.println(nameAndAddress.getPostcode());
	}

}
