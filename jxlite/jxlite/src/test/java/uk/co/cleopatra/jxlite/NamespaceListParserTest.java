package uk.co.cleopatra.jxlite;

import java.util.HashSet;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import uk.co.cleopatra.jxlite.NamespaceListParser;

public class NamespaceListParserTest {

	private NamespaceContext ctx;

	@Before
	public void SetUp() {
		ctx = NamespaceListParser.parse("a=http://xxx", "b=http://xxx",
				"c=http://yyy");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testGetPrefixes() {
		HashSet<String> result = new HashSet<String>();
		Iterator<String> iter = ctx.getPrefixes("http://xxx");
		while (iter.hasNext()) {
			result.add(iter.next());
		}
		assertEquals(2, result.size());
		assertTrue(result.contains("a"));
		assertTrue(result.contains("b"));
	}
	
	@Test
	public void testGetPrefix() {
		String result = ctx.getPrefix("http://yyy");
		assertEquals("c", result);
	}
	
	@Test
	public void testGetNamespaceURI() {
		String result = ctx.getNamespaceURI("b");
		assertEquals("http://xxx", result);
	}

}
