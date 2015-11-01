package uk.co.cleopatra.jxlite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the default return value for a string method 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DefaultString {
	/**
	 * Default value to be used if there is no corresponding data in the input XML
	 */
	String value();
}
