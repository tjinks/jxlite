package uk.co.cleopatra.jxlite.converters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.TimeZone;

import org.junit.Test;

import uk.co.cleopatra.jxlite.converters.CalendarConverter;

public class CalendarConverterTest {

	@Test
	public void testDateTime() {
		CalendarConverter converter = new CalendarConverter();
		Calendar result = (Calendar) converter
				.stringToObject("2014-04-21T23:30:15");
		assertEquals(2014, result.get(Calendar.YEAR));
		assertEquals(3, result.get(Calendar.MONTH));
		assertEquals(21, result.get(Calendar.DAY_OF_MONTH));
		assertEquals(23, result.get(Calendar.HOUR_OF_DAY));
		assertEquals(30, result.get(Calendar.MINUTE));
		assertEquals(15, result.get(Calendar.SECOND));
	}

	@Test
	public void testDefaultTimeZone() {
		CalendarConverter converter = new CalendarConverter();
		TimeZone result = ((Calendar) converter
				.stringToObject("2014-04-21T23:30:15")).getTimeZone();
		TimeZone defaultTimeZone = Calendar.getInstance().getTimeZone();
		assertTrue(defaultTimeZone.getRawOffset() == result.getRawOffset());
	}
	
	@Test
	public void testPositiveTimeZone() {
		CalendarConverter converter = new CalendarConverter();
		TimeZone result = ((Calendar) converter
				.stringToObject("2014-04-21T23:30:15+02:10")).getTimeZone();
		assertEquals(130 * 60 * 1000, result.getRawOffset());
	}

	@Test
	public void testNegativeTimeZone() {
		CalendarConverter converter = new CalendarConverter();
		TimeZone result = ((Calendar) converter
				.stringToObject("2014-04-21T23:30:15-02:10")).getTimeZone();
		assertEquals(-130 * 60 * 1000, result.getRawOffset());
	}
}
