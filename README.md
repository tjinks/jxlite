# jxlite
JxLite is a Java XML parser that is designed to simplify parsing XML documents such as configuration files. JxLite uses annotations to map from XML to Java. Unlike some XML frameworks, it doesn't require an additional build step or rely on external configuration data to describe this mapping.

Here's an example. Suppose we have an XML document like this:

```
<?xml version="1.0" encoding="UTF-8"?>
<person age="21">
	<forename>Fred</forename>
	<surname>Bloggs</surname>
</person>
```
The following is a complete Java program that will parse this XML and output the data it contains. (The program assumes that the input XML is available as a resource on the Java class path).

```
package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.annotations.Path;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class IntroExample {

	public interface Person {
		@Path("forename")
		String getForename();

		@Path("surname")
		String getSurname();

		@Path("@age")
		int getAge();
	}

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader.
		  parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/person.xml");
		Unmarshaller<Person> unmarshaller = JxLite.unmarshallerFor(Person.class);
		
		/*
		 * The following line creates an instance of a class that implements the
		 * Person interface and that holds the data read from the input XML
		 */
		Person person = unmarshaller.unmarshal(doc);

		System.out.println("Forename = " + person.getForename());
		System.out.println("Surname  = " + person.getSurname());
		System.out.println("Age      = " + person.getAge());
	}
}
```
Of course this example is very simple. The documentation (under jxlite/doc) explains how JxLite can be used to deal with more realistic input.


