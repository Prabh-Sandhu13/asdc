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

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public IUserCoursesRepository getUserCoursesRepository() {
        return userCoursesRepository;
    }

    public void setUserCoursesRepository(IUserCoursesRepository userCoursesRepository) {
        this.userCoursesRepository = userCoursesRepository;
    }

    public IUserCoursesService getUserCoursesService() {
        return userCoursesService;
    }

    public void setUserCoursesService(IUserCoursesService userCoursesService) {
        this.userCoursesService = userCoursesService;
    }

    public IPasswordEncryptor getPasswordEncryptor() {
        return passwordEncryptor;
    }

    public void setPasswordEncryptor(IPasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public IUserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public IDBConfiguration getDbConfiguration() {
        return dbConfiguration;
    }

    public void setDbConfiguration(IDBConfiguration dbConfiguration) {
        this.dbConfiguration = dbConfiguration;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public IForgotPasswordService getForgotPasswordService() {
        return forgotPasswordService;
    }

    public void setForgotPasswordService(IForgotPasswordService forgotPasswordService) {
        this.forgotPasswordService = forgotPasswordService;
    }

    public IForgotPasswordRepository getForgotPasswordRepository() {
        return forgotPasswordRepository;
    }

    public void setForgotPasswordRepository(IForgotPasswordRepository forgotPasswordRepository) {
        this.forgotPasswordRepository = forgotPasswordRepository;
    }

    public ITokenGenerator getTokenGenerator() {
        return tokenGenerator;
    }

    public void setTokenGenerator(ITokenGenerator tokenGenerator) {
        this.tokenGenerator = tokenGenerator;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public SimpleMailMessage getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    public JavaMailSenderImpl getJavaMailSender() {
        return mailSender;
    }

    public ICourseService getCourseService() {
        return courseService;
    }

    public void setCourseService(ICourseService courseService) {
        this.courseService = courseService;
    }

    public ICourseRepository getCourseRepository() {
        return courseRepository;
    }

    public void setCourseRepository(ICourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public IStudentRepository getStudentRepository() {
        return studentRepository;
    }

    public void setStudentRepository(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public IPasswordHistoryRepository getPasswordHistoryRepository() {
        return passwordHistoryRepository;
    }

    public void setPasswordHistoryRepository(IPasswordHistoryRepository passwordHistoryRepository) {
        this.passwordHistoryRepository = passwordHistoryRepository;
    }

    public IPasswordHistoryService getPasswordHistoryService() {
        return passwordHistoryService;
    }

    public void setPasswordHistoryService(IPasswordHistoryService passwordHistoryService) {
        this.passwordHistoryService = passwordHistoryService;
    }

    public IPolicyRepository getPolicyRepository() {
        return policyRepository;
    }

    public void setPolicyRepository(IPolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public IPolicyService getPolicyService() {
        return policyService;
    }

    public void setPolicyService(IPolicyService policyService) {
        this.policyService = policyService;
    }

    public IQuestionManagerRepository getQuestionManagerRepository() {
        return questionManagerRepository;
    }

    public void setQuestionManagerRepository(IQuestionManagerRepository questionManagerRepository) {
        this.questionManagerRepository = questionManagerRepository;
    }

    public IQuestionManagerService getQuestionManagerService() {
        return questionManagerService;
    }

    public void setQuestionManagerService(IQuestionManagerService questionManagerService) {
        this.questionManagerService = questionManagerService;
    }
}
