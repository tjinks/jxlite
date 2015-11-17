package uk.co.cleopatra.jxlite.rxpath;


import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;

import uk.co.cleopatra.jxlite.JxLiteClientException;
import uk.co.cleopatra.jxlite.rxpath.RXPathComponent.Type;

public class RXPathParser {
	private final Lexer lexer;
	private final NamespaceContext namespaceContext;
	private final RXPathExpressionImpl result;
	private String namePart1, namePart2;
	

	public RXPathParser(String rxp, NamespaceContext namespaceContext) {
		lexer = new Lexer(rxp);
		this.namespaceContext = namespaceContext;
		result = new RXPathExpressionImpl();
		parseInternal();
	}
	
	public RXPathExpression getExpression() {
		return result;
	}

	private void parseInternal() {
		State state = new BetweenComponents();
		while (!(state instanceof Finished)) {
			state = state.processToken(lexer.getCurrentTokenType(), lexer.getCurrentToken());
			if (state == null) {
				throw invalidRXPath();
			}
			lexer.nextToken();
		}
		return;
	}
	
	private void addElement() {
		String namespace, localName;
		if (namePart2 == null) {
			namespace = XMLConstants.NULL_NS_URI;
			localName = namePart1;
		} else {
			namespace = namespaceContext.getNamespaceURI(namePart1);
			localName = namePart2;
		}
		RXPathComponent c = new RXPathComponent(Type.ELEMENT, namespace, localName);
		result.addComponent(c);
	}
	
	private void addAttribute() {
		String namespace, localName;
		if (namePart2 == null) {
			namespace = null;
			localName = namePart1;
		} else {
			namespace = namespaceContext.getNamespaceURI(namePart1);
			localName = namePart2;
		}
		RXPathComponent c = new RXPathComponent(Type.ATTRIBUTE, namespace, localName);
		result.addComponent(c);
	}
	
	private static JxLiteClientException invalidRXPath() {
		return new JxLiteClientException("Invalid XPath");
	}

	private interface State {
		State processToken(TokenType tokenType, String token);
	}

	private class Finished implements State {

		@Override
		public State processToken(TokenType tokenType, String token) {
			return null;
		}
		
	}
	
	private class BetweenComponents implements State {
		BetweenComponents() {
			namePart1 = null;
			namePart2 = null;
		}

		@Override
		public State processToken(TokenType tokenType, String token) {
			switch (tokenType) {
			case AT:
				return new BeforeAttribute();
			case NCNAME:
				namePart1 = token;
				return new AfterElementNamePart1();
			default:
				return null;
			}
		}
	}

	private class AfterElementNamePart1 implements State {

		@Override
		public State processToken(TokenType tokenType, String token) {
			switch (tokenType) {
			case END_OF_INPUT:
				addElement();
				return new Finished();
			case COLON:
				return new AfterElementNamePrefix();
			case FORWARD_SLASH:
				addElement();
				return new BetweenComponents();
			default:
				return null;
			}
		}
	}

	private class AfterElementNamePrefix implements State {

		@Override
		public State processToken(TokenType tokenType, String token) {
			switch (tokenType) {
			case NCNAME:
				namePart2 = token;
				addElement();
				return new AfterElementNamePart2();
			default:
				return null;
			}
		}
	}

	private class AfterElementNamePart2 implements State {

		@Override
		public State processToken(TokenType tokenType, String token) {
			switch (tokenType) {
			case FORWARD_SLASH:
				return new BetweenComponents();
			case END_OF_INPUT:
				return new Finished();
			default:
				return null;
			}
		}
	}
	
	private class BeforeAttribute implements State {

		@Override
		public State processToken(TokenType tokenType, String token) {
			switch (tokenType) {
			case NCNAME:
				namePart1 = token;
				return new AfterAttributeNamePart1();
			default:
				return null;
			}
		}
	}

	private class AfterAttributeNamePart1 implements State {

		@Override
		public State processToken(TokenType tokenType, String token) {
			switch (tokenType) {
			case COLON:
				return new AfterAttributeNamePrefix();
			case END_OF_INPUT:
				addAttribute();
				return new Finished();
			default:
				return null;
			}
		}
	}

	private class AfterAttributeNamePrefix implements State {

		@Override
		public State processToken(TokenType tokenType, String token) {
			switch (tokenType) {
			case NCNAME:
				namePart2 = token;
				addAttribute();
				return new AfterAttributeNamePart2();
			default:
				return null;
			}

		}
	}

	private class AfterAttributeNamePart2 implements State {

		@Override
		public State processToken(TokenType tokenType, String token) {
			switch (tokenType) {
			case END_OF_INPUT:
				return new Finished();
			default:
				return null;
			}
		}
	}
}
