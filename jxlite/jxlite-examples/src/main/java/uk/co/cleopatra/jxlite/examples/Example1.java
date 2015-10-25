package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class Example1 {

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader
				.parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/person.xml");
		Unmarshaller<Person> unmarshaller = JxLite
				.unmarshallerFor(Person.class);
		Person person = unmarshaller.unmarshal(doc);
		System.out.println(person.getForename() + " " + person.getSurname()
				+ ", " + person.getAge());
		System.out.println(person);
	}
}
