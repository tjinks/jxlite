package uk.co.cleopatra.jxlite;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import javax.xml.xpath.XPathException;

import org.xml.sax.SAXException;

/**
 * Base class for exceptions thrown by JxLite.
 * <p>
 * Note that JxLiteException extends RuntimeException, so these exceptions do not have to be
 * declared in a throws declaration.
 */
public abstract class JxLiteException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/*
	 * These exceptions 'probably' indicate an error by the client program.
	 */
	@SuppressWarnings("unchecked")
	private static final List<?> PROBABLE_CLIENT_ERRORS = Arrays.asList(SAXException.class, XPathException.class,
			FileNotFoundException.class);

	protected JxLiteException() {
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

	/**
	 * Converts an arbitrary exception to an appropriate instance of
	 * JxLiteException
	 * 
	 * @param e
	 *            Exception to convert
	 * @return If e is already an instance of JxLiteException then e, otherwise
	 *         a JxLiteException instance that wraps e
	 */
	public static JxLiteException convert(Throwable e) {
		if (e instanceof JxLiteException) {
			return (JxLiteException) e;
		} else if (PROBABLE_CLIENT_ERRORS.contains(e.getClass())) {
			return new JxLiteClientException(e);
		} else {
			return new JxLiteSystemException(e);
		}
	}
}
