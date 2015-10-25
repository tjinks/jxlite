package uk.co.cleopatra.jxlite;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import uk.co.cleopatra.jxlite.converters.NodeConverter;

/**
 * Converts a set of (zero or more) XML nodes that are identified by an XPath
 * expression to a Java object.
 */
class XPathConverter {

	private final NodeConverter nodeConverter;
	private final Multiplicity multiplicity;
	private final XPathExpression xpathExpression;
	private final CollectionType collectionType;

	/**
	 * Constructs a new XPathConverter instance.
	 * 
	 * @param xpathExpression
	 *            The XPathExpression to evaluate
	 * @param multiplicity
	 *            The expected multiplicity of the nodes returned by the
	 *            expression
	 * @param collectionType
	 *            If multiplicity is greater than 1, the Java collection type to
	 *            return
	 * @param nodeConverter
	 *            A {@link NodeConverter} that will be used to convert
	 *            individual nodes to Java objects
	 */
	XPathConverter(XPathExpression xpathExpression, Multiplicity multiplicity,
			CollectionType collectionType, NodeConverter nodeConverter) {
		super();

		boolean valid = false;
		switch (multiplicity) {
		case ZERO_OR_ONE:
		case EXACTLY_ONE:
			if (collectionType == CollectionType.NONE) {
				valid = true;
			}
			break;
		case ZERO_OR_MORE:
		case ONE_OR_MORE:
			if (collectionType != CollectionType.NONE) {
				valid = true;
			}
		}
		if (!valid) {
			throw new IllegalArgumentException(
					"Inconsistency between multiplicity and collectionType arguments");
		}

		this.nodeConverter = nodeConverter;
		this.multiplicity = multiplicity;
		this.collectionType = collectionType;
		this.xpathExpression = xpathExpression;
	}

	Object convert(Element context) {
		try {
			NodeList nodeList = (NodeList) xpathExpression.evaluate(context,
					XPathConstants.NODESET);
			checkMultiplicity(nodeList.getLength());
			switch (multiplicity) {
			case ZERO_OR_ONE:
			case EXACTLY_ONE:
				if (nodeList.getLength() == 1) {
					return nodeConverter.convert(nodeList.item(0));
				} else {
					return null;
				}
			default:
				return convertMultiItemNodeList(nodeList);
			}
		} catch (Exception e) {
			throw JxLiteException.convert(e);
		}
	}

	private Object convertMultiItemNodeList(NodeList nodeList) {
		switch (collectionType) {
		case LIST:
			return convertNodeListToCollection(nodeList);
		case ARRAY:
			return convertNodeListToArray(nodeList);
		default:
			throw new IllegalStateException("Invalid value for collectionType");
		}
	}

	private Object convertNodeListToArray(NodeList nodeList) {
		int length = nodeList.getLength();
		Object result = Array
				.newInstance(nodeConverter.getObjectType(), length);
		for (int i = 0; i < length; i++) {
			Array.set(result, i, nodeConverter.convert(nodeList.item(i)));
		}
		return result;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object convertNodeListToCollection(NodeList nodeList) {
		Collection result = new ArrayList();
		for (int i = 0; i < nodeList.getLength(); i++) {
			result.add(nodeConverter.convert(nodeList.item(i)));
		}
		return result;
	}

	/*
	 * Check that the number of nodes returned by the XPath expression matches
	 * the expected multiplicity.
	 */
	private void checkMultiplicity(int count) {
		switch (multiplicity) {
		case ZERO_OR_ONE:
			if (count > 1) {
				throw new JxLiteClientException(
						"Node may appear at most once in input document");
			}
			break;
		case EXACTLY_ONE:
			if (count != 1) {
				throw new JxLiteClientException(
						"Node must appear exactly once in input document");
			}
			break;
		case ZERO_OR_MORE:
			break;
		case ONE_OR_MORE:
			if (count == 0) {
				throw new JxLiteClientException(
						"Node must appear at least once in input document");
			}
			break;
		}
	}
}
