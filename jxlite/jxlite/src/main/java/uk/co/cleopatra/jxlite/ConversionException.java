package uk.co.cleopatra.jxlite;

public class ConversionException extends JxLiteException {
	private static final long serialVersionUID = 1L;
	
	private final String interfaceName, xPath;

	public ConversionException(String message, String interfaceName, String xPath) {
		super(message);
		this.interfaceName = interfaceName;
		this.xPath = xPath;
	}

	public ConversionException(Throwable cause, String interfaceName, String xPath) {
		super(cause);
		this.interfaceName = interfaceName;
		this.xPath = xPath;
	}

	public String getInterfaceName() {
		return interfaceName;
	}

	public String getxPath() {
		return xPath;
	}
}
