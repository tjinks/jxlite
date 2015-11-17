package uk.co.cleopatra.jxlite.rxpath;

import org.junit.Test;
import static org.junit.Assert.*;

public class LexerTest {
	
	@Test
	public void testLexer() {
		Lexer lexer = new Lexer("a:x12/@abc");
		assertEquals("a", lexer.getCurrentToken());
		assertEquals(TokenType.NCNAME, lexer.getCurrentTokenType());
		
		lexer.nextToken();
		assertEquals(":", lexer.getCurrentToken());
		assertEquals(TokenType.COLON, lexer.getCurrentTokenType());
		
		lexer.nextToken();
		assertEquals("x12", lexer.getCurrentToken());
		assertEquals(TokenType.NCNAME, lexer.getCurrentTokenType());

		lexer.nextToken();
		assertEquals("/", lexer.getCurrentToken());
		assertEquals(TokenType.FORWARD_SLASH, lexer.getCurrentTokenType());

		lexer.nextToken();
		assertEquals("@", lexer.getCurrentToken());
		assertEquals(TokenType.AT, lexer.getCurrentTokenType());

		lexer.nextToken();
		assertEquals("abc", lexer.getCurrentToken());
		assertEquals(TokenType.NCNAME, lexer.getCurrentTokenType());

		lexer.nextToken();
		assertNull(lexer.getCurrentToken());
		assertEquals(TokenType.END_OF_INPUT, lexer.getCurrentTokenType());
	}
}
