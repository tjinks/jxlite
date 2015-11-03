package uk.co.cleopatra.jxlite.converters;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.JxLiteException;
import uk.co.cleopatra.jxlite.XmlTestBase;
import uk.co.cleopatra.jxlite.converters.BooleanConverter;
import uk.co.cleopatra.jxlite.converters.NodeConverter;

public class BooleanConverterTest extends XmlTestBase {
	private NodeConverter converter;

	@Before
	public void setUp() throws Exception {
		converter = new BooleanConverter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMapElement() {
		Node node = doc.getElementsByTagName("boolElement").item(0);
		Object result = converter.convert(node);
		assertEquals(true, result);
	}

	@Test
	public void testMapAttr() {
		Element element = (Element) doc.getElementsByTagName("boolAttr").item(0);
		Attr attr = element.getAttributeNode("value");
		Object result = converter.convert(attr);
		assertEquals(false, result);
	}

	@Test
	public void testMapInvalidInput() {
		boolean exceptionThrown = false;
		Node node = doc.getElementsByTagName("textElement").item(0);
		try {
			converter.convert(node);
		} catch (JxLiteException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

}
