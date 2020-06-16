package CSCI5308.GroupFormationTool;

public final class DomainConstants {

	// For Student CSV
	public static final int newStudents = 0;
	public static final int oldStudents = 1;
	public static final int badData = 2;

	// For Mail Services
	public static final String smtpHost = "smtp.gmail.com";
	public static final int smtpPort = 587;
	public static final String mailUserName = "noreply.group22@gmail.com";
	public static final String mailPassword = "dalhousiemacs";

	// Domain Url
	public static final String domainUrl = "http://localhost:8080";

	// Forgot Password
	public static final String forgotPasswordSubject = "Complete Password Reset!";
	public static final String forgotPasswordText = "To reset your password, follow this link: ";

	// Student Registration Mail
	public static final String registrationSubject = "New Student Registration!";

	// Questions
	public static final int numeric = 1;
	public static final int MCQOne = 2;
	public static final int MCQMultiple = 3;
	public static final int freeText = 4;

}
