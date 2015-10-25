package uk.co.cleopatra.jxlite.converters.datetime;

import static org.junit.Assert.*;

import org.junit.Test;

public class DateTimeParserTest {
	private static final String DT_WITH_NO_TIMEZONE = "2014-04-21T09:33:57";
	private static final String DT_WITH_Z_TIMEZONE = DT_WITH_NO_TIMEZONE + "Z";
	private static final String DT_WITH_POSITIVE_TIMEZONE = DT_WITH_NO_TIMEZONE + "+05:45";
	private static final String DT_WITH_NEGATIVE_TIMEZONE = DT_WITH_NO_TIMEZONE + "-07:00";
	
	private DateTimeParser parser;

	@Test
	public void testGetYear() {
		parser = new DateTimeParser(DT_WITH_NO_TIMEZONE);
		assertEquals(2014, parser.getYear());
	}
	
	@Test
	public void testGetMonth() {
		parser = new DateTimeParser(DT_WITH_NO_TIMEZONE);
		assertEquals(4, parser.getMonth());
	}

	@Test
	public void testGetDay() {
		parser = new DateTimeParser(DT_WITH_NO_TIMEZONE);
		assertEquals(21, parser.getDay());
	}

	@Test
	public void testGetHour() {
		parser = new DateTimeParser(DT_WITH_NO_TIMEZONE);
		assertEquals(9, parser.getHour());
	}

	@Test
	public void testGetMinute() {
		parser = new DateTimeParser(DT_WITH_NO_TIMEZONE);
		assertEquals(33, parser.getMinute());
	}

	@Test
	public void testGetSecond() {
		parser = new DateTimeParser(DT_WITH_NO_TIMEZONE);
		assertEquals(57, parser.getSecond());
	}

	@Test
	public void testGetDefaultTzOffset() {
		parser = new DateTimeParser(DT_WITH_NO_TIMEZONE);
		assertNull(parser.getTzString());
	}

	@Test
	public void testGetZTzOffset() {
		parser = new DateTimeParser(DT_WITH_Z_TIMEZONE);
		assertEquals("GMT+00:00", parser.getTzString());
	}
	
	@Test
	public void testGetPositiveTzOffset() {
		parser = new DateTimeParser(DT_WITH_POSITIVE_TIMEZONE);
		assertEquals("GMT+05:45", parser.getTzString());
	}
	
	@Test
	public void testGetNegativeTzOffset() {
		parser = new DateTimeParser(DT_WITH_NEGATIVE_TIMEZONE);
		assertEquals("GMT-07:00", parser.getTzString());
	}
}
