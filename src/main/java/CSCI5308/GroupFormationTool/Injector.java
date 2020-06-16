package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Repository.*;
import CSCI5308.GroupFormationTool.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.Security.TokenGenerator;
import CSCI5308.GroupFormationTool.Service.*;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// Important for Dependency Injection
public class Injector {

    private static Injector instance = null;

    private IDBConfiguration dbConfiguration;
    private IUserRepository userRepository;
    private IUserService userService;
    private IPasswordEncryptor passwordEncryptor;

    private ITokenGenerator tokenGenerator;
    private IForgotPasswordService forgotPasswordService;
    private IForgotPasswordRepository forgotPasswordRepository;
    private IMailService mailService;
    private IPasswordHistoryService passwordHistoryService;
    private IPasswordHistoryRepository passwordHistoryRepository;
    private SimpleMailMessage mailMessage;
    private JavaMailSenderImpl mailSender;
    private ICourseService courseService;
    private ICourseRepository courseRepository;
    private IUserCoursesRepository userCoursesRepository;
    private IUserCoursesService userCoursesService;
    private IStudentRepository studentRepository;
    private IStudentService studentService;
    private IPolicyRepository policyRepository;
    private IPolicyService policyService;
    private IQuestionManagerService questionManagerService;
    private IQuestionManagerRepository questionManagerRepository;

    private Injector() {

        dbConfiguration = new DBConfiguration();
        userRepository = new UserRepository();
        userService = new UserService();
        passwordEncryptor = new BCryptEncryption();
        forgotPasswordService = new ForgotPasswordService();
        forgotPasswordRepository = new ForgotPasswordRepository();
        tokenGenerator = new TokenGenerator();
        passwordHistoryService = new PasswordHistoryService();
        passwordHistoryRepository = new PasswordHistoryRepository();
        mailService = new MailService();
        mailMessage = new SimpleMailMessage();
        mailSender = new JavaMailSenderImpl();
        courseService = new CourseService();
        courseRepository = new CourseRepository();
        userCoursesRepository = new UserCoursesRepository();
        userCoursesService = new UserCoursesService();
        studentRepository = new StudentRepository();
        studentService = new StudentService();
        policyRepository = new PolicyRepository();
        policyService = new PolicyService();
        questionManagerService = new QuestionManagerService();
        questionManagerRepository = new QuestionManagerRepository();
    }

    public static Injector instance() {

        if (instance == null) {
            instance = new Injector();
        }
        return instance;
    }

    public IUserCoursesRepository getUserCoursesRepository() {
        return userCoursesRepository;
    }

    public IUserCoursesService getUserCoursesService() {
        return userCoursesService;
    }

    public IPasswordEncryptor getPasswordEncryptor() {
        return passwordEncryptor;
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public IDBConfiguration getDbConfiguration() {
        return dbConfiguration;
    }

    public IUserService getUserService() {
        return userService;
    }

    public IForgotPasswordService getForgotPasswordService() {
        return forgotPasswordService;
    }

    public IForgotPasswordRepository getForgotPasswordRepository() {
        return forgotPasswordRepository;
    }

    public ITokenGenerator getTokenGenerator() {
        return tokenGenerator;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public SimpleMailMessage getMailMessage() {
        return mailMessage;
    }

    public JavaMailSenderImpl getJavaMailSender() {
        return mailSender;
    }

    public ICourseService getCourseService() {
        return courseService;
    }

    public ICourseRepository getCourseRepository() {
        return courseRepository;
    }

    public IStudentRepository getStudentRepository() {
        return studentRepository;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public IPasswordHistoryRepository getPasswordHistoryRepository() {
        return passwordHistoryRepository;
    }

    public IPasswordHistoryService getPasswordHistoryService() {
        return passwordHistoryService;
    }

    public IPolicyRepository getPolicyRepository() {
        return policyRepository;
    }

    public IPolicyService getPolicyService() {
        return policyService;
    }

    public IQuestionManagerRepository getQuestionManagerRepository() {
        return questionManagerRepository;
    }

    public IQuestionManagerService getQuestionManagerService() {
        return questionManagerService;
    }
}
