package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class Example2 {

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader
				.parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/people.xml");
		Unmarshaller<People> unmarshaller = JxLite
				.unmarshallerFor(People.class);
		People people = unmarshaller.unmarshal(doc);
		for (Person person : people.getPeople()) {
			System.out.println(person.getForename() + " " + person.getSurname()
					+ ", " + person.getAge());
		}
	}
}
