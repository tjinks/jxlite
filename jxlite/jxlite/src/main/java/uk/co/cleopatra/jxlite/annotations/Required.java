package uk.co.cleopatra.jxlite.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the input XML must explicitly supply a return value for this method.
 * I.e. the XPath specified by the {@link Path} annotation must return at least one XML node. 
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Required {

}
