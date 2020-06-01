package CSCI5308.GroupFormationTool.ErrorHandling;

public class InvalidDetailsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public InvalidDetailsException(String message) {
		super(message);
	}
}
