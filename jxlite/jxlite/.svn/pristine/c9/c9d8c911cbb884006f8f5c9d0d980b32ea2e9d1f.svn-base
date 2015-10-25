package uk.co.cleopatra.jxlite.converters.datetime;

import java.util.Calendar;
import java.util.Date;

import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.converters.NodeConverterBase;

public class DateConverter extends NodeConverterBase {
	private final static CalendarConverter CALENDAR_CONVERTER = new CalendarConverter();

	public DateConverter() {
		super(Date.class);
	}

	@Override
	protected Object doConvert(Node node) throws Exception {
		Calendar cal = (Calendar) CALENDAR_CONVERTER.convert(node);
		return cal.getTime();
	}

}
