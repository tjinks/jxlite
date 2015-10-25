package uk.co.cleopatra.jxlite.converters;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.XmlTestBase;
import uk.co.cleopatra.jxlite.converters.NodeConverter;
import uk.co.cleopatra.jxlite.converters.StringConverter;

public class StringConverterTest extends XmlTestBase {
	
	private NodeConverter converter;

	@Before
	public void setUp() throws Exception {
		converter = new StringConverter();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testMapElement() {
		Node node = doc.getElementsByTagName("textElement").item(0);
		Object result = converter.convert(node);
		assertEquals("abc123", result);
	}
	
	@Test
	public void testMapAttr() {
		Element element = (Element) doc.getElementsByTagName("textAttr").item(0);
		Attr attr = element.getAttributeNode("value");
		Object result = converter.convert(attr);
		assertEquals("xyz", result);
	}

}
