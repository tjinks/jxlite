package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class Example7 {

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader
				.parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/textoutput.xml");
		Unmarshaller<TextOutput> unmarshaller = JxLite
				.unmarshallerFor(TextOutput.class);
		TextOutput textOutput = unmarshaller.unmarshal(doc);
		System.out.println(textOutput.getColor());
	}

}
