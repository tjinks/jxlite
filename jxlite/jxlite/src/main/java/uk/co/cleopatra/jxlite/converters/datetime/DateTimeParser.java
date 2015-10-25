package uk.co.cleopatra.jxlite.converters.datetime;

import uk.co.cleopatra.jxlite.converters.datetime.Token.Type;

class DateTimeParser {
	private final Tokenizer tokenizer;
	private int year, month, day, hour, minute, second;
	private String tzString;
	private Token current;

	DateTimeParser(String text) {
		tokenizer = new Tokenizer(text);
		current = tokenizer.next();
		year = getInt(0, 9999);
		skip('-');
		month = getInt(1, 12);
		skip('-');
		day = getInt(1, 31);
		skip('T');
		hour = getInt(0, 23);
		skip(':');
		minute = getInt(0, 59);
		skip(':');
		second = getInt(0, 59);
		tzString = parseTz();
		if (current.getType() != Type.END) {
			throw new JxLiteDateFormatException();
		}
	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	public String getTzString() {
		return tzString;
	}

	private int getInt(int min, int max) {
		boolean valid = false;
		int result = 0;
		switch (current.getType()) {
		case NUMBER:
			result = (Integer) current.getValue();
			valid = (result >= min && result <= max);
			break;
		default:
			break;
		}
		if (!valid) {
			throw new JxLiteDateFormatException();
		}
		advance();
		return result;
	}

	private void skip(char c) {
		if (!match(c)) {
			throw new JxLiteDateFormatException();
		}
		advance();
	}

	private void advance() {
		current = tokenizer.next();
	}

	private boolean match(char c) {
		switch (current.getType()) {
		case CHARACTER:
			return current.getValue().equals(c);
		default:
			return false;
		}
	}

	private String parseTz() {
		if (current.getType() == Type.END) {
			return null;
		}
		int minutes = 0, hours = 0;
		char sign = '+';
		if (match('Z')) {
			advance();
		} else {
			if (match('-')) {
				sign = '-';
				advance();
			} else if (match('+')) {
				advance();
			} else {
				throw new JxLiteDateFormatException();
			}
			hours = getInt(0, 14);
			skip(':');
			minutes = getInt(0, 59);
		}
		return String.format("GMT%c%02d:%02d", sign, hours, minutes);
	}
}
