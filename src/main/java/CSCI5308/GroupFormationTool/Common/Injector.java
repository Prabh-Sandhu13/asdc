package CSCI5308.GroupFormationTool.Common;

import CSCI5308.GroupFormationTool.Course.*;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.Mail.IMailManager;
import CSCI5308.GroupFormationTool.Mail.MailManager;
import CSCI5308.GroupFormationTool.Password.*;
import CSCI5308.GroupFormationTool.Question.IQuestionAdminRepository;
import CSCI5308.GroupFormationTool.Question.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.Question.QuestionAdminRepository;
import CSCI5308.GroupFormationTool.Question.QuestionManagerRepository;
import CSCI5308.GroupFormationTool.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserRepository;
import CSCI5308.GroupFormationTool.User.User;
import CSCI5308.GroupFormationTool.User.UserRepository;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Injector {

    private static Injector instance = null;

    private IDBConfiguration dbConfiguration;
    private IUserRepository userRepository;
    private IUser user;
    private IPasswordEncryptor passwordEncryptor;

    private ITokenGenerator tokenGenerator;
    private IForgotPasswordManager forgotPasswordManager;
    private IForgotPasswordRepository forgotPasswordRepository;
    private IMailManager mailManager;
    private IPasswordHistoryManager passwordHistoryManager;
    private IPasswordHistoryRepository passwordHistoryRepository;
    private SimpleMailMessage mailMessage;
    private JavaMailSenderImpl mailSender;
    private ICourseRepository courseRepository;
    private IUserCoursesRepository userCoursesRepository;
    private IStudentRepository studentRepository;
    private IStudentCSV studentCSV;
    private IPolicyRepository policyRepository;
    private IPolicy policy;
    private IQuestionManagerRepository questionManagerRepository;
    private IQuestionAdminRepository questionAdminRepository;

    private Injector() {

        dbConfiguration = new DBConfiguration();
        userRepository = new UserRepository();
        user = new User();
        passwordEncryptor = new BCryptEncryption();
        forgotPasswordManager = new ForgotPasswordManager();
        forgotPasswordRepository = new ForgotPasswordRepository();
        tokenGenerator = new TokenGenerator();
        passwordHistoryManager = new PasswordHistoryManager();
        passwordHistoryRepository = new PasswordHistoryRepository();
        mailManager = new MailManager();
        mailMessage = new SimpleMailMessage();
        mailSender = new JavaMailSenderImpl();
        courseRepository = new CourseRepository();
        userCoursesRepository = new UserCoursesRepository();
        studentRepository = new StudentRepository();
        studentCSV = new StudentCSV();
        policyRepository = new PolicyRepository();
        policy = new Policy();
        questionManagerRepository = new QuestionManagerRepository();
        questionAdminRepository = new QuestionAdminRepository();
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

    public IUser getUser() {
        return user;
    }

    public IForgotPasswordManager getForgotPasswordManager() {
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

    public IMailManager getMailManager() {
        return mailManager;
    }

    public void setMailManager(IMailManager mailManager) {
        this.mailManager = mailManager;
    }

    public SimpleMailMessage getMailMessage() {
        return mailMessage;
    }

    public JavaMailSenderImpl getJavaMailSender() {
        return mailSender;
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

    public IStudentCSV getStudentCSV() {
        return studentCSV;
    }

    public IPasswordHistoryRepository getPasswordHistoryRepository() {
        return passwordHistoryRepository;
    }

    public void setPasswordHistoryRepository(IPasswordHistoryRepository passwordHistoryRepository) {
        this.passwordHistoryRepository = passwordHistoryRepository;
    }

    public IPasswordHistoryManager getPasswordHistoryManager() {
        return passwordHistoryManager;
    }

    public void setPasswordHistoryManager(IPasswordHistoryManager passwordHistoryManager) {
        this.passwordHistoryManager = passwordHistoryManager;
    }

    public IPolicyRepository getPolicyRepository() {
        return policyRepository;
    }

    public void setPolicyRepository(IPolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public IPolicy getPolicy() {
        return policy;
    }

    public void setPolicy(IPolicy policy) {
        this.policy = policy;
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

}
