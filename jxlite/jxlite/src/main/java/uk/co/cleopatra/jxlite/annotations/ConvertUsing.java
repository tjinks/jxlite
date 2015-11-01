package uk.co.cleopatra.jxlite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import uk.co.cleopatra.jxlite.converters.NodeConverter;

/**
 * Applied to an interface method to specify the converter that is to be used to
 * convert the contents of an XML node to the return value of the method
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ConvertUsing {
	/**
	 * The class that will be used to convert the contents of an XML node to a
	 * Java object. This class must implement
	 * {@link uk.co.cleopatra.jxlite.converters.NodeConverter}.
	 */
	Class<? extends NodeConverter>value();
}
