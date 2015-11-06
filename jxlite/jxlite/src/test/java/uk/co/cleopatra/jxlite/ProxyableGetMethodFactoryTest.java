package uk.co.cleopatra.jxlite;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.NamespaceListParser;
import uk.co.cleopatra.jxlite.ProxyableGetMethod;
import uk.co.cleopatra.jxlite.ProxyableGetMethodFactory;
import uk.co.cleopatra.jxlite.annotations.ConvertUsing;
import uk.co.cleopatra.jxlite.annotations.DefaultInt;
import uk.co.cleopatra.jxlite.annotations.DefaultString;
import uk.co.cleopatra.jxlite.annotations.Path;
import uk.co.cleopatra.jxlite.converters.IntegerConverter;
import uk.co.cleopatra.jxlite.converters.NodeConverter;
import uk.co.cleopatra.jxlite.converters.NodeConverterBase;
import uk.co.cleopatra.jxlite.converters.NodeConverterRegistry;
import uk.co.cleopatra.jxlite.converters.TextContentConverter;

public class ProxyableGetMethodFactoryTest extends XmlTestBase {
	private ProxyableGetMethodFactory factory;

	private static class DoubleItConverter extends NodeConverterBase {
		private final IntegerConverter conv = new IntegerConverter();

		public DoubleItConverter() {
			super(Integer.TYPE);
		}

		@Override
		protected Object doConvert(Node node) throws Exception {
			int tmp = (Integer) conv.convert(node);
			return 2 * tmp;
		}

	}

	private static class BigIntConverter extends TextContentConverter {
		public BigIntConverter() {
			super(BigInteger.class);
		}

		@Override
		public Object stringToObject(String nodeValue) {
			return new BigInteger(nodeValue);
		}

	}

	private interface Main {
		@Path("a:intElement")
		int getIntElement();

		@Path("a:missingIntElement")
		@DefaultInt(42)
		int getMissingIntElement();

		@Path("a:missingIntElementWithNoDefault")
		int getMissingIntElementWithNoDefault();

		@Path("a:missingIntegerElementWithNoDefault")
		Integer getMissingIntegerElementWithNoDefault();

		@Path("a:boolElement")
		boolean getBoolElement();

		@Path("a:textAttr/@value")
		String getTextAttr();

		@Path("a:textElement")
		MockUnmarshaller.Intf getIntf();

		@Path("a:listElement/a:item")
		List<String> getList();

		@Path("a:listElement/a:item")
		Set<String> getSet();

		@Path("a:listElement/a:item")
		String[] getArray();

		@Path("a:intElement")
		Element getElement();

		@Path("a:doubleItElement")
		@ConvertUsing(DoubleItConverter.class)
		int getDoubleItElement();

		@Path("a:bigIntElement")
		@DefaultString("42")
		BigInteger getBigIntElement();
	}

	@Before
	public void setUp() throws Exception {
		NodeConverterRegistry registry = new NodeConverterRegistry();
		NodeConverter elementConverter = new NodeConverter() {

			@Override
			public Class<?> getObjectType() {
				return Element.class;
			}

			@Override
			public Object convert(Node node) {
				return (Element) node;
			}
		};
		registry.register(elementConverter);
		registry.register(new BigIntConverter());
		factory = new ProxyableGetMethodFactory(new MockUnmarshallerFactory(),
				NamespaceListParser.parse("a=http://xxx"), registry);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetMethodForInt() throws Exception {
		Method method = Main.class.getMethod("getIntElement", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		int result = (Integer) pgm.get(doc.getDocumentElement());
		assertEquals(666, result);
	}

	@Test
	public void testGetMethodForMissingInt() throws Exception {
		Method method = Main.class.getMethod("getMissingIntElement", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		int result = (Integer) pgm.get(doc.getDocumentElement());
		assertEquals(42, result);
	}

	@Test
	public void testGetMethodForMissingIntWithNoDefault() throws Exception {
		Method method = Main.class.getMethod("getMissingIntElementWithNoDefault", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		boolean exceptionThrown = false;
		try {
			pgm.get(doc.getDocumentElement());
		} catch (JxLiteClientException e) {
			exceptionThrown = true;
		}
		assertTrue(exceptionThrown);
	}

	@Test
	public void testGetMethodForBoolean() throws Exception {
		Method method = Main.class.getMethod("getBoolElement", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		boolean result = (Boolean) pgm.get(doc.getDocumentElement());
		assertEquals(true, result);
	}

	@Test
	public void testGetMethodForString() throws Exception {
		Method method = Main.class.getMethod("getTextAttr", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		String result = (String) pgm.get(doc.getDocumentElement());
		assertEquals("xyz", result);
	}

	@Test
	public void testGetMethodForIntf() throws Exception {
		Method method = Main.class.getMethod("getIntf", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		MockUnmarshaller.Intf result = (MockUnmarshaller.Intf) pgm.get(doc.getDocumentElement());
		assertEquals("textElement", result.getValue());
	}

	@Test
	public void testGetMethodForList() throws Exception {
		Method method = Main.class.getMethod("getList", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		@SuppressWarnings("unchecked")
		List<String> result = (List<String>) pgm.get(doc.getDocumentElement());
		assertEquals(3, result.size());
		assertEquals("Item1", result.get(0));
		assertEquals("Item2", result.get(1));
		assertEquals("Item3", result.get(2));
	}

	@Test
	public void testGetMethodForArray() throws Exception {
		Method method = Main.class.getMethod("getArray", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		String[] result = (String[]) pgm.get(doc.getDocumentElement());
		assertEquals(3, result.length);
		assertEquals("Item1", result[0]);
		assertEquals("Item2", result[1]);
		assertEquals("Item3", result[2]);
	}

	@Test
	public void testCustomGetMethod() throws Exception {
		Method method = Main.class.getMethod("getElement", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		Element result = (Element) pgm.get(doc.getDocumentElement());
		assertEquals("intElement", result.getLocalName());
	}

	@Test
	public void testConvertUsing() throws Exception {
		Method method = Main.class.getMethod("getDoubleItElement", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		int result = (Integer) pgm.get(doc.getDocumentElement());
		assertEquals(6, result);
	}

	@Test
	public void testDefaultValueConversion() throws Exception {
		Method method = Main.class.getMethod("getBigIntElement", new Class[0]);
		ProxyableGetMethod pgm = factory.methodFor(method);
		BigInteger result = (BigInteger) pgm.get(doc.getDocumentElement());
		assertEquals(42, result.intValue());
	}

}
