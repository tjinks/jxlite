package uk.co.cleopatra.jxlite.converters;

import javax.xml.namespace.QName;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Node;

import uk.co.cleopatra.jxlite.ThreadLocalXPathFactory;
import uk.co.cleopatra.jxlite.JxLiteException;

/**
 * This is a utility class that provides functionality likely to be common to all
 * NodeConverter implementations.
 * <p>
 * Derived classes must override the {@link #doConvert(Node)} method.
 */
public abstract class NodeConverterBase implements NodeConverter {
	private final Class<?> objectType;

	private static final ThreadLocal<XPathExpression> CURRENT_NODE = new ThreadLocal<XPathExpression>() {
		@Override
		protected XPathExpression initialValue() {
			try {
				XPath xp = ThreadLocalXPathFactory.get().newXPath();
				return xp.compile(".");
			} catch (XPathExpressionException e) {
				throw JxLiteException.convert(e);
			}
		}
	};
	

	@Override
	public Object convert(Node node) {
		try {
			return doConvert(node);
		} catch (JxLiteException e) {
			throw e;
		} catch (Exception e) {
			throw JxLiteException.convert(e);
		}
	}
	
	protected static Object evaluate(Node node, QName returnType) throws Exception {
		return CURRENT_NODE.get().evaluate(node, returnType);
	}
	
	protected abstract Object doConvert(Node node) throws Exception;

	@Override
	public Class<?> getObjectType() {
		return objectType;
	}

	protected NodeConverterBase(Class<?> objectType) {
		super();
		this.objectType = objectType;
	}

}
