package uk.co.cleopatra.jxlite.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.JxLiteClientException;
import uk.co.cleopatra.jxlite.XmlTestBase;

public class IntegerConverterTest extends XmlTestBase {
	private NodeConverter converter;

	@Before
	public void setUp() throws Exception {
		converter = new IntegerConverter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMapElement() {
		Node node = doc.getElementsByTagName("intElement").item(0);
		Object result = converter.convert(node);
		assertEquals(666, result);
	}

	@Test
	public void testMapAttr() {
		Element element = (Element) doc.getElementsByTagName("intAttr").item(0);
		Attr attr = element.getAttributeNode("value");
		Object result = converter.convert(attr);
		assertEquals(123, result);
	}

	@Test
	public void testMapInvalidInput() {
		boolean exceptionThrown = false;
		Node node = doc.getElementsByTagName("textElement").item(0);
		try {
			converter.convert(node);
		} catch (JxLiteClientException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

}
