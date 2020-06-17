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
    public static final String domainUrl = "http://formgroups22.herokuapp.com";

    // Forgot Password
    public static final String forgotPasswordSubject = "Complete Password Reset!";
    public static final String forgotPasswordText = "To reset your password, follow this link: ";

    // Student Registration Mail
    public static final String registrationSubject = "New Student Registration!";

    // Questions
    public static final int numeric = 1;
    public static final int MCQOne = 2;
    public static final int MCQMultiple = 3;
    public final static long invalidData = 0;
    public final static long sqlError = -1;

    // Database
    public static final String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_22_TEST?useSSL=false"
            + "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    public static final String USER = "CSCI5308_22_TEST_USER";
    public static final String PASSWORD = "CSCI5308_22_TEST_22546";

}
