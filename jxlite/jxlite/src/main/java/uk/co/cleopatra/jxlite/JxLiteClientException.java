package uk.co.cleopatra.jxlite;

public class JxLiteClientException extends JxLiteException {

	private static final long serialVersionUID = 1L;
	
	public JxLiteClientException() {
	}

	public JxLiteClientException(String message) {
		super(message);
	}

	public JxLiteClientException(Throwable cause) {
		super(cause);
	}

	public JxLiteClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
