package uk.co.cleopatra.jxlite.converters;

import org.w3c.dom.Node;

/**
 * Instances of this interface convert XML nodes to Java objects of a
 * particular type.
 */
public interface NodeConverter {

	/**
	 * Converts an XML node to a Java object.
	 * 
	 * @param node
	 *            The XML node
	 * @return The resulting Java object. This must be an instance of the
	 *         class/interface returned by {@link #getObjectType()}.
	 */
	Object convert(Node node);

	/**
	 * The type of Java object returned by this converter.
	 * 
	 * @return A java {@link java.lang.Class} instance
	 */
	Class<?> getObjectType();
}
