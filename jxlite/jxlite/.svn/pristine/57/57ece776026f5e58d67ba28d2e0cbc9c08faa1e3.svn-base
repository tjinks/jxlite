package uk.co.cleopatra.jxlite.converters.datetime;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uk.co.cleopatra.jxlite.converters.datetime.Token.Type;

public class TokenizerTest {
	private Tokenizer tokenizer;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testIntAtEndOfInput() {
		tokenizer = new Tokenizer("1234");
		Token token = tokenizer.next();
		checkToken(Type.NUMBER, 1234, token);
	}
	
	@Test
	public void testIntBeforeEndOfInput() {
		tokenizer = new Tokenizer("1234-");
		Token token = tokenizer.next();
		checkToken(Type.NUMBER, 1234, token);
	}
	
	@Test
	public void testCharacter() {
		tokenizer = new Tokenizer("-");
		Token token = tokenizer.next();
		checkToken(Type.CHARACTER, '-', token);
	}
	
	@Test
	public void testEnd() {
		tokenizer = new Tokenizer("");
		Token token = tokenizer.next();
		checkToken(Type.END, null, token);
	}
	
	@Test
	public void testCursorAdvance() {
		tokenizer = new Tokenizer("1234:");
		Token token = tokenizer.next();
		checkToken(Type.NUMBER, 1234, token);
		token = tokenizer.next();
		checkToken(Type.CHARACTER, ':', token);
		token = tokenizer.next();
		checkToken(Type.END, null, token);
		token = tokenizer.next();
		checkToken(Type.END, null, token);
	}
	
	private void checkToken(Token.Type expectedType, Object expectedValue, Token token) {
		assertEquals(expectedType, token.getType());
		assertEquals(expectedValue, token.getValue());
	}
}
