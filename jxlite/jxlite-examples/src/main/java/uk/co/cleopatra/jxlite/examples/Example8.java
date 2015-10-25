package uk.co.cleopatra.jxlite.examples;

import org.w3c.dom.Document;

import uk.co.cleopatra.jxlite.JxLite;
import uk.co.cleopatra.jxlite.NodeConverterRegistry;
import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class Example8 {

	public static void main(String[] args) {
		Document doc = XmlDocumentLoader
				.parseResource("uk/co/cleopatra/jxlite/examples/xmldocs/textoutput2.xml");
		NodeConverterRegistry registry = JxLite.getNodeConverterRegistry();
		registry.register(new ColorConverter2());
		Unmarshaller<TextOutputList> unmarshaller = JxLite
				.unmarshallerFor(TextOutputList.class, registry);
		TextOutputList result = unmarshaller.unmarshal(doc);
		System.out.println(result.getDefaultColor());
		TextOutput firstItem = result.getOutputs()[0];
		System.out.println(firstItem.getColor());
	}

}
