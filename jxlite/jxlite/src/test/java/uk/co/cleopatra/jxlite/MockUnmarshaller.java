package uk.co.cleopatra.jxlite;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import uk.co.cleopatra.jxlite.Unmarshaller;

class MockUnmarshaller implements Unmarshaller<MockUnmarshaller.Intf> {
	
	interface Intf {
		String getValue();
	}

	@Override
	public Intf unmarshal(final Element element) {
		return new Intf() {

			@Override
			public String getValue() {
				return element.getLocalName();
			}
			
		};
	}

	@Override
	public Intf unmarshal(Document doc) {
		return null;
	}

}
