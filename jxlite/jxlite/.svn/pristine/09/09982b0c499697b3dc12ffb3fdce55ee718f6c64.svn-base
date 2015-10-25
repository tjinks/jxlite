package uk.co.cleopatra.jxlite;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import uk.co.cleopatra.jxlite.Unmarshaller;
import uk.co.cleopatra.jxlite.MockUnmarshaller.Intf;
import uk.co.cleopatra.jxlite.converters.InterfaceConverter;
import uk.co.cleopatra.jxlite.converters.NodeConverter;

public class InterfaceConverterTest extends XmlTestBase {
	private NodeConverter converter;
	private Unmarshaller<MockUnmarshaller.Intf> unmarshaller = new MockUnmarshaller();

	@Before
	public void setUp() throws Exception {
		converter = new InterfaceConverter(MockUnmarshaller.class, unmarshaller);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvert() {
		Element element = doc.getDocumentElement();
		MockUnmarshaller.Intf result = (Intf) converter.convert(element);
		assertEquals("root", result.getValue());
	}

}
