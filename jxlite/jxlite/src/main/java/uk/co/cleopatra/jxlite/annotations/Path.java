package uk.co.cleopatra.jxlite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies the XPath that identifies from where in the input XML the value(s) of
 * a property are to be retrieved.  
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Path {
	/**
	 * An XPath expression. (If the associated method returns an array or collection
	 * type then this may match multiple nodes in the input XML).  
	 * @see NamespaceList
	 */
	String value();
}
