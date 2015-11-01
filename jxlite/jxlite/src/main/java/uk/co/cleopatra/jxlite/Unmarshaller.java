package uk.co.cleopatra.jxlite;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Instances of this interface unmarshal XML data into Java objects.
 * @param <T> The type of Java object into which XML data will be unmarshalled
 */
public interface Unmarshaller<T> {
	/**
	 * Unmarshals an XML element
	 * @param element Element to unmarshal
	 * @return Java object
	 */
	T unmarshal(Element element);
	
	/**
	 * Unmarshals an entire XML document. Equivalent to {@code unmarshal(doc.getDocumentElement())}
	 * @param doc Document to unmarshal
	 * @return Java object
	 */
	T unmarshal(Document doc);
}
