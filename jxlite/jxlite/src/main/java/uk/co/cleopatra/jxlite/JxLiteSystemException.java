package uk.co.cleopatra.jxlite;

/**
 * Class for exceptions that probably indicate an error within JxLite itself. Instances of this exception
 * 'should' never be seen!
 */
public class JxLiteSystemException extends JxLiteException {

	private static final long serialVersionUID = 1L;

	public JxLiteSystemException() {
	}

	public JxLiteSystemException(String message) {
		super(message);
	}

	public JxLiteSystemException(Throwable cause) {
		super(cause);
	}

	public JxLiteSystemException(String message, Throwable cause) {
		super(message, cause);
	}

}
