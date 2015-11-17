package uk.co.cleopatra.jxlite.rxpath;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.rxpath.RXPathComponent.Type;
import uk.co.cleopatra.jxlite.utils.XmlDocumentLoader;

public class RXPathExpressionImplTest {
	private static final String XML_RESOURCE = "uk/co/cleopatra/jxlite/rxpath/people.xml";
	private static final String NAMESPACE = "http://rxpath/test";
	
	private Document doc;
	private RXPathExpressionImpl path = new RXPathExpressionImpl();
	
	@Before
	public void initialize() {
		doc = XmlDocumentLoader.parseResource(XML_RESOURCE);
	}
	
	@Test
	public void testAttributePath() {
		path.addComponent(new RXPathComponent(Type.ELEMENT, NAMESPACE, "person"));
		path.addComponent(new RXPathComponent(Type.ATTRIBUTE, null, "id"));
		List<Node> result = path.evaluate(doc.getDocumentElement());
		assertEquals(2, result.size());
		Attr attr = (Attr) result.get(0);
		assertEquals("1", attr.getValue());
		attr = (Attr) result.get(1);
		assertEquals("2", attr.getValue());
	}
	
	@Test
	public void testSingleElementPath() {
		path.addComponent(new RXPathComponent(Type.ELEMENT, NAMESPACE, "person"));
		path.addComponent(new RXPathComponent(Type.ELEMENT, NAMESPACE, "role"));
		List<Node> result = path.evaluate(doc.getDocumentElement());
		assertEquals(1, result.size());
		assertEquals("developer", result.get(0).getTextContent());
	}
	
	@Test
	public void testMultiElementPath() {
		path.addComponent(new RXPathComponent(Type.ELEMENT, NAMESPACE, "person"));
		path.addComponent(new RXPathComponent(Type.ELEMENT, NAMESPACE, "surname"));
		List<Node> result = path.evaluate(doc.getDocumentElement());
		assertEquals(2, result.size());
		assertEquals("march", result.get(0).getTextContent());
		assertEquals("dinn", result.get(1).getTextContent());
	}
}
