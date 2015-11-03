package uk.co.cleopatra.jxlite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import uk.co.cleopatra.jxlite.converters.BooleanConverterTest;
import uk.co.cleopatra.jxlite.converters.DoubleConverterTest;
import uk.co.cleopatra.jxlite.converters.IntegerConverterTest;
import uk.co.cleopatra.jxlite.converters.InterfaceConverterTest;
import uk.co.cleopatra.jxlite.converters.StringConverterTest;
import uk.co.cleopatra.jxlite.converters.datetime.CalendarConverterTest;
import uk.co.cleopatra.jxlite.converters.datetime.DateTimeParserTest;
import uk.co.cleopatra.jxlite.converters.datetime.TokenizerTest;

@RunWith(Suite.class)
@SuiteClasses({ IntegerConverterTest.class,
		DoubleConverterTest.class, CalendarConverterTest.class,
		DateTimeParserTest.class, TokenizerTest.class,
		BooleanConverterTest.class, InterfaceConverterTest.class,
		NamespaceListParserTest.class, ProxyableGetMethodFactoryTest.class,
		UnmarshallerFactoryImplTest.class, UnmarshallerImplTest.class,
		StringConverterTest.class })
public class AllTests {

}
