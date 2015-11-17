package uk.co.cleopatra.jxlite.rxpath;

class Lexer {
	private final char[] input;
	private int index;
	private String currentToken;
	private TokenType currentTokenType;
	
	Lexer(String s) {
		input = s.toCharArray();
		index = 0;
		nextToken();
	}
	
	String getCurrentToken() {
		return currentToken;
	}

	TokenType getCurrentTokenType() {
		return currentTokenType;
	}

	void nextToken() {
		if (index >= input.length) {
			currentToken = null;
			currentTokenType = TokenType.END_OF_INPUT;
			return;
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(input[index]);
		if (isNCNameStartChar(input[index++])) {
			while (index < input.length && isNCNameChar(input[index])) {
				sb.append(input[index++]);
			}
		}
		currentToken = sb.toString();
		char c = currentToken.charAt(0);
		switch (c) {
		case '/':
			currentTokenType = TokenType.FORWARD_SLASH;
			break;
		case '@':
			currentTokenType = TokenType.AT;
			break;
		case ':':
			currentTokenType = TokenType.COLON;
			break;
		default:
			if (isNCNameStartChar(c)) {
				currentTokenType = TokenType.NCNAME;
			} else {
				currentTokenType = TokenType.OTHER;
			}
		}
	}

	private static boolean isNCNameStartChar(char c) {
		if (c == '_') {
			return true;
		}
		if (inRange(c, 'a', 'z') || inRange(c, 'A', 'Z')) {
			return true;
		}
		if (inRange(c, '\u00c0', '\u00c6')) {
			return true;
		}
		if (inRange(c, '\u00f8', '\u02ff')) {
			return true;
		}
		if (inRange(c, '\u0370', '\u037d')) {
			return true;
		}
		if (inRange(c, '\u037f', '\u1fff')) {
			return true;
		}
		if (inRange(c, '\u200c', '\u200d')) {
			return true;
		}
		if (inRange(c, '\u2070', '\u218f')) {
			return true;
		}
		if (inRange(c, '\u2c00', '\u2fef')) {
			return true;
		}
		if (inRange(c, '\u3001', '\ud7ff')) {
			return true;
		}
		if (inRange(c, '\uf900', '\ufdcf')) {
			return true;
		}
		if (inRange(c, '\ufdf0', '\ufffd')) {
			return true;
		}
		if (inRange(c, '\u200c', '\u200d')) {
			return true;
		}
		return false;
	}
	
	private static boolean isNCNameChar(char c) {
		if (isNCNameStartChar(c)) {
			return true;
		}
		if (c == '-' || c == '.' || c == '\u00b7') {
			return true;
		}
		if (inRange(c, '0', '9')) {
			return true;
		}
		if (inRange(c, '\u0300', '\u036f')) {
			return true;
		}
		if (inRange(c, '\u203f', '\u2040')) {
			return true;
		}
		return false;
	}
	
	private static boolean inRange(char c, char start, char end) {
		return c >= start && c <= end;
	}
}
