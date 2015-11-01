package uk.co.cleopatra.jxlite.converters.datetime;

import uk.co.cleopatra.jxlite.converters.JxLiteDateFormatException;
import uk.co.cleopatra.jxlite.converters.datetime.Token.Type;

/**
 * An instance of this class holds data obtained by parsing an XML date/time.
 */
public class DateTimeParser {
	private final Tokenizer tokenizer;
	private int year, month, day, hour, minute, second;
	private String tzString;
	private Token current;

	/**
	 * Initialises a DateTimeParser instance from an xs:datetime formatted string
	 * @param xsDateTime xs:datetime string (e.g. 2015-12-25T13:45:00Z)
	 */
	public DateTimeParser(String xsDateTime) {
		tokenizer = new Tokenizer(xsDateTime);
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

	/**
	 * Returns the year represented by this instance
	 * @return Year (e.g. 2015)
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns the month represented by this instance
	 * @return Month (1-12)
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Returns the day of the month represented by this instance
	 * @return Day (1-31)
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Returns the hour represented by this instance
	 * @return Hour (0-23)
	 */
	public int getHour() {
		return hour;
	}

	/**
	 * Returns the minute represented by this instance
	 * @return Minute (0-59)
	 */
	public int getMinute() {
		return minute;
	}

	/**
	 * Returns the second represented by this instance
	 * @return Second (0-59)
	 */
	public int getSecond() {
		return second;
	}

	/**
	 * Returns the timezone represented by this instance 
	 * @return The timezone extracted from the xs:datetime passed to the constructor, or null 
	 * if it did not include a timezone. The value returned is always of the form GMT[+/-]HH:MM.
	 * E.g. GMT+05:45
	 */
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
