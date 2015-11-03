package uk.co.cleopatra.jxlite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies a list of namespaces and namespace prefixes that are to be used
 * when interpreting XPath expressions
 * 
 * @see Path
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface NamespaceList {
	/**
	 * One or more strings of the form &lt;prefix&gt;=&lt;namespace&gt; - e.g.
	 * abc=http://mycompany.com/myproduct
	 */
	String[] value();
}
