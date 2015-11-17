package uk.co.cleopatra.jxlite.rxpath;

import org.w3c.dom.Node;

class RXPathComponent {
	enum Type {
		ATTRIBUTE, ELEMENT
	}

	private final Type type;
	private final String namespace;
	private final String localName;

	RXPathComponent(Type type, String namespace, String localName) {
		super();
		this.type = type;
		this.namespace = namespace;
		this.localName = localName;
	}

	boolean matchesNode(Node n) {
		switch (n.getNodeType()) {
		case Node.ATTRIBUTE_NODE:
			if (type != Type.ATTRIBUTE) {
				return false;
			}
			break;
		case Node.ELEMENT_NODE:
			if (type != Type.ELEMENT) {
				return false;
			}
			break;
		default:
			return false;
		}
		if (!localName.equals(n.getLocalName())) {
			return false;
		}
		if (namespace == null && n.getNamespaceURI() == null) {
			return true;
		}
		if (namespace == null || n.getNamespaceURI() == null) {
			return false;
		}
		return namespace.equals(n.getNamespaceURI());
	}

	Type getType() {
		return type;
	}

	String getNamespace() {
		return namespace;
	}

	String getLocalName() {
		return localName;
	}
}
