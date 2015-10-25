package uk.co.cleopatra.jxlite;

import javax.xml.namespace.NamespaceContext;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;

class XPathCompiler {

	static XPathExpression compile(String xpath, NamespaceContext namespaceContext) {
		try {
			XPath xp = ThreadLocalXPathFactory.get().newXPath();
			xp.setNamespaceContext(namespaceContext);
			return xp.compile(xpath);
		} catch (Exception e) {
			throw JxLiteException.convert(e);
		}
	}
}
