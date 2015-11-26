package uk.co.cleopatra.jxlite.rxpath;

import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * A 'restricted' XPath expression.
 */
public interface RXPathExpression {
	List<Node> evaluate(Element e);
}
