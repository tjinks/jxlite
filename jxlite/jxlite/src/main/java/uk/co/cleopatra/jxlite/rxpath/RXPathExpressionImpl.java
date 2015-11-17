package uk.co.cleopatra.jxlite.rxpath;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.rxpath.RXPathComponent.Type;

class RXPathExpressionImpl implements RXPathExpression {
	private final ArrayList<RXPathComponent> components = new ArrayList<RXPathComponent>();

	void addComponent(RXPathComponent c) {
		components.add(c);
	}

	@Override
	public List<Node> evaluate(Element e) {
		ArrayList<Node> result = new ArrayList<Node>();
		evaluateFrom(e, 0, result);
		return result;
	}

	private void evaluateFrom(Element e, int index, List<Node> result) {
		RXPathComponent c = components.get(index);
		String namespace = c.getNamespace();
		String localName = c.getLocalName();
		if (index == components.size() - 1) {
			if (c.getType() == Type.ATTRIBUTE) {
				Node n = getAttribute(e, namespace, localName);
				if (n != null) {
					result.add(n);
				}
			} else {
				result.addAll(getChildElementsByName(e, namespace, localName));
			}
		} else {
			for (Element child : getChildElementsByName(e, namespace, localName)) {
				evaluateFrom(child, index + 1, result);
			}
		}
	}

	private static Node getAttribute(Element e, String namespace, String localName) {
		return e.getAttributeNodeNS(namespace, localName);
	}

	private static List<Element> getChildElementsByName(Element e, String namespace, String localName) {
		ArrayList<Element> result = new ArrayList<Element>();
		Node child = e.getFirstChild();
		while (child != null) {
			if (child.getNodeType() == Node.ELEMENT_NODE && child.getNamespaceURI().equals(namespace)
					&& child.getLocalName().equals(localName)) {
				result.add((Element) child);
			}
			child = child.getNextSibling();
		}
		return result;
	}
}
