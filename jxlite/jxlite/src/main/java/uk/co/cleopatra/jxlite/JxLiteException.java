package uk.co.cleopatra.jxlite;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import javax.xml.xpath.XPathException;

import org.xml.sax.SAXException;

public abstract class JxLiteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	private static final List<?> PROBABLE_CLIENT_ERRORS = Arrays.asList(
			SAXException.class, XPathException.class,
			FileNotFoundException.class);

	public JxLiteException() {
	}

	protected JxLiteException(String message) {
		super(message);
	}

	protected JxLiteException(Throwable cause) {
		super(cause);
	}

	protected JxLiteException(String message, Throwable cause) {
		super(message, cause);
	}

	public static JxLiteException convert(Throwable e) {
		if (e instanceof JxLiteException) {
			return (JxLiteException) e;
		}
		if (PROBABLE_CLIENT_ERRORS.contains(e.getClass())) {
			return new JxLiteClientException(e);
		}
		return new JxLiteSystemException(e);
	}

}
