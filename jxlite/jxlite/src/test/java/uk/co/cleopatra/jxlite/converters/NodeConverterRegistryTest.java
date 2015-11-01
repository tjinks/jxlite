package uk.co.cleopatra.jxlite.converters;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.converters.NodeConverter;
import uk.co.cleopatra.jxlite.converters.NodeConverterRegistry;

public class NodeConverterRegistryTest {
	private NodeConverterRegistry registry;
	

	@Before
	public void setUp() throws Exception {
		registry = new NodeConverterRegistry();
	}

	@After
	public void tearDown() throws Exception {
		registry = null;
	}

	@Test
	public void testRegisterExisting() {
		NodeConverter stringConverter = new NodeConverter() {
			
			@Override
			public Class<?> getObjectType() {
				return String.class;
			}
			
			@Override
			public Object convert(Node node) {
				return null;
			}
		};
		
		registry.register(stringConverter);
		NodeConverter result = registry.lookup(String.class);
		assertTrue(result == stringConverter);
	}

	@Test
	public void testRegisterNew() {
		NodeConverter dateConverter = new NodeConverter() {
			
			@Override
			public Class<?> getObjectType() {
				return Date.class;
			}
			
			@Override
			public Object convert(Node node) {
				return null;
			}
		};
		
		registry.register(dateConverter);
		NodeConverter result = registry.lookup(Date.class);
		assertTrue(result == dateConverter);
	}

	@Test
	public void testLookup() {
		NodeConverter result = registry.lookup(Boolean.TYPE);
		assertTrue(result instanceof BooleanClassConverter);
	}

}
