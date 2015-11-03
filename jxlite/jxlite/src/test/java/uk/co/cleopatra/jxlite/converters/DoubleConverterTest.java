package uk.co.cleopatra.jxlite.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.JxLiteClientException;
import uk.co.cleopatra.jxlite.XmlTestBase;

public class DoubleConverterTest extends XmlTestBase {
	private NodeConverter converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new DoubleConverter();
	}

	@Test
	public void testMapElement() {
		Node node = doc.getElementsByTagName("doubleElement").item(0);
		double result = (Double) converter.convert(node);
		assertEquals(123.45, result, 1E-6);
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
