package exceptions;

public class InvalidEncryptionKeyException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidEncryptionKeyException(String errorMessage) {
		super(errorMessage);
	}

}
