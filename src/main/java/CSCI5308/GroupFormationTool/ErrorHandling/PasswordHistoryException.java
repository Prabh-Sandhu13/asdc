package CSCI5308.GroupFormationTool.ErrorHandling;

public class PasswordHistoryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public PasswordHistoryException(String message) {
		super(message);
	}

}