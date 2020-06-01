package CSCI5308.GroupFormationTool.ErrorHandling;

public class TokenAlreadyExistException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public TokenAlreadyExistException(String message) {
		super(message);
	}

}


