package CSCI5308.GroupFormationTool.Common;

import CSCI5308.GroupFormationTool.Course.*;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.Mail.IMailService;
import CSCI5308.GroupFormationTool.Mail.MailService;
import CSCI5308.GroupFormationTool.Password.*;
import CSCI5308.GroupFormationTool.Question.*;
import CSCI5308.GroupFormationTool.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.Password.TokenGenerator;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.User.IUserRepository;
import CSCI5308.GroupFormationTool.User.IUserService;
import CSCI5308.GroupFormationTool.User.UserRepository;
import CSCI5308.GroupFormationTool.User.UserService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Injector {

    private static Injector instance = null;

    private IDBConfiguration dbConfiguration;
    private IUserRepository userRepository;
    private IUserService userService;
    private IPasswordEncryptor passwordEncryptor;

    private ITokenGenerator tokenGenerator;
    private IForgotPasswordManager forgotPasswordManager;
    private IForgotPasswordRepository forgotPasswordRepository;
    private IMailService mailService;
    private IPasswordHistoryManager passwordHistoryManager;
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
    private IQuestionAdminService questionAdminService;
    private IQuestionManagerRepository questionManagerRepository;
    private IQuestionAdminRepository questionAdminRepository;

    private Injector() {

        dbConfiguration = new DBConfiguration();
        userRepository = new UserRepository();
        userService = new UserService();
        passwordEncryptor = new BCryptEncryption();
        forgotPasswordManager = new ForgotPasswordManager();
        forgotPasswordRepository = new ForgotPasswordRepository();
        tokenGenerator = new TokenGenerator();
        passwordHistoryManager = new PasswordHistoryManager();
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
        questionAdminRepository = new QuestionAdminRepository();
        questionAdminService = new QuestionAdminService();
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

    public void setUserCoursesRepository(IUserCoursesRepository userCoursesRepository) {
        this.userCoursesRepository = userCoursesRepository;
    }

    public IUserCoursesService getUserCoursesService() {
        return userCoursesService;
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

    public IUserService getUserService() {
        return userService;
    }

    public IForgotPasswordManager getForgotPasswordService() {
        return forgotPasswordManager;
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

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
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

    public IPasswordHistoryRepository getPasswordHistoryRepository() {
        return passwordHistoryRepository;
    }

    public void setPasswordHistoryRepository(IPasswordHistoryRepository passwordHistoryRepository) {
        this.passwordHistoryRepository = passwordHistoryRepository;
    }

    public IPasswordHistoryManager getPasswordHistoryService() {
        return passwordHistoryManager;
    }

    public void setPasswordHistoryService(IPasswordHistoryManager passwordHistoryManager) {
        this.passwordHistoryManager = passwordHistoryManager;
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

    public IQuestionAdminRepository getQuestionAdminRepository() {
        return questionAdminRepository;
    }

    public void setQuestionAdminRepository(IQuestionAdminRepository questionAdminRepository) {
        this.questionAdminRepository = questionAdminRepository;
    }
    
    public IQuestionManagerService getQuestionManagerService() {
        return questionManagerService;
    }
    
    public IQuestionAdminService getQuestionAdminService() {
        return questionAdminService;
    }
    
}
