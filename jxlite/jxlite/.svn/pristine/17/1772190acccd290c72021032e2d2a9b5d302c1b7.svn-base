package uk.co.cleopatra.jxlite.converters.datetime;

import uk.co.cleopatra.jxlite.converters.datetime.Token.Type;

class Tokenizer {
	private final String input;
	private int index;

	Tokenizer(String input) {
		this.input = input;
		index = 0;
	}

	Token next() {
		if (index >= input.length()) {
			return new Token(Type.END, null);
		}
		char c = input.charAt(index);
		if (Character.isDigit(c)) {
			StringBuilder digits = new StringBuilder();
			digits.append(c);
			while (true) {
				index++;
				if (index >= input.length()) {
					break;
				}
				c = input.charAt(index);
				if (!Character.isDigit(c)) {
					break;
				}
				digits.append(c);
			}
			return new Token(Type.NUMBER, Integer.parseInt(digits.toString()));
		} else {
			index++;
			return new Token(Type.CHARACTER, c);
		}
	}
}
