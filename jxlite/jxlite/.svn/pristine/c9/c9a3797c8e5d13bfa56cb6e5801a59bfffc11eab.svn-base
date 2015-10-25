package uk.co.cleopatra.jxlite.converters.datetime;

class Token {
	enum Type {
		CHARACTER, NUMBER, END
	}
	
	private final Type type;
	private final Object value;
	
	public Type getType() {
		return type;
	}

	public Object getValue() {
		return value;
	}

	public Token(Type type, Object value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	@Override
	public String toString() {
		switch (type) {
		case CHARACTER:
			return "CHARACTER '" + value + "'";
		case END:
			return "END";
		case NUMBER:
			return "NUMBER " + value;
		}
		return "";
	}
}
