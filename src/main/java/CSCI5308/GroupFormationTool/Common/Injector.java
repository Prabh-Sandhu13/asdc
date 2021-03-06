package CSCI5308.GroupFormationTool.Common;

import CSCI5308.GroupFormationTool.Course.*;
import CSCI5308.GroupFormationTool.Database.DatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.GroupFormation.GroupFormationAbstractFactory;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationAbstractFactory;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationManager;
import CSCI5308.GroupFormationTool.GroupFormation.IGroupFormationRepository;
import CSCI5308.GroupFormationTool.Mail.IMailAbstractFactory;
import CSCI5308.GroupFormationTool.Mail.IMailManager;
import CSCI5308.GroupFormationTool.Mail.MailAbstractFactory;
import CSCI5308.GroupFormationTool.Password.*;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Question.IQuestionAdminRepository;
import CSCI5308.GroupFormationTool.Question.IQuestionManagerRepository;
import CSCI5308.GroupFormationTool.Question.QuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Security.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.Security.ISecurityAbstractFactory;
import CSCI5308.GroupFormationTool.Security.SecurityAbstractFactory;
import CSCI5308.GroupFormationTool.Survey.ISurveyAbstractFactory;
import CSCI5308.GroupFormationTool.Survey.ISurveyRepository;
import CSCI5308.GroupFormationTool.Survey.SurveyAbstractFactory;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.IUserRepository;
import CSCI5308.GroupFormationTool.User.UserAbstractFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class Injector {

    private static Injector instance = null;
    private IUserAbstractFactory userAbstractFactory;
    private IDatabaseAbstractFactory databaseAbstractFactory;
    private IQuestionAbstractFactory questionAbstractFactory;
    private ISecurityAbstractFactory securityAbstractFactory;
    private ICourseAbstractFactory courseAbstractFactory;
    private IMailAbstractFactory mailAbstractFactory;
    private IPasswordAbstractFactory passwordAbstractFactory;
    private IGroupFormationAbstractFactory groupFormationAbstractFactory;
    private ISurveyAbstractFactory surveyAbstractFactory;
    private IDBConfiguration dbConfiguration;
    private IUserRepository userRepository;
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
    private ISurveyRepository surveyRepository;
    private IUserCoursesRepository userCoursesRepository;
    private IStudentRepository studentRepository;
    private IPolicyRepository policyRepository;
    private IQuestionManagerRepository questionManagerRepository;
    private IQuestionAdminRepository questionAdminRepository;
    private IGroupFormationRepository groupFormationRepository;
    private IGroupFormationManager groupFormationManager;

    private Injector() {
        userAbstractFactory = new UserAbstractFactory();
        databaseAbstractFactory = new DatabaseAbstractFactory();
        questionAbstractFactory = new QuestionAbstractFactory();
        securityAbstractFactory = new SecurityAbstractFactory();
        courseAbstractFactory = new CourseAbstractFactory();
        mailAbstractFactory = new MailAbstractFactory();
        passwordAbstractFactory = new PasswordAbstractFactory();
        groupFormationAbstractFactory = new GroupFormationAbstractFactory();
        surveyAbstractFactory = new SurveyAbstractFactory();
        dbConfiguration = databaseAbstractFactory.createDBConfigurationInstance();
        userRepository = userAbstractFactory.createUserRepositoryInstance();
        passwordEncryptor = securityAbstractFactory.createBCryptEncryptionInstance();
        forgotPasswordManager = passwordAbstractFactory.createForgotPasswordManagerInstance();
        forgotPasswordRepository = passwordAbstractFactory.createForgotPasswordRepositoryInstance();
        tokenGenerator = passwordAbstractFactory.createTokenGeneratorInstance();
        passwordHistoryManager = passwordAbstractFactory.createPasswordHistoryManagerInstance();
        passwordHistoryRepository = passwordAbstractFactory.createPasswordHistoryRepositoryInstance();
        mailManager = mailAbstractFactory.createMailManagerInstance();
        mailMessage = mailAbstractFactory.createSimpleMailMessageInstance();
        mailSender = mailAbstractFactory.createJavaMailSenderInstance();
        courseRepository = courseAbstractFactory.createCourseRepository();
        surveyRepository = surveyAbstractFactory.createSurveyRepositoryInstance();
        userCoursesRepository = courseAbstractFactory.createUserCoursesRepository();
        studentRepository = courseAbstractFactory.createStudentRepository();
        policyRepository = passwordAbstractFactory.createPolicyRepository();
        questionManagerRepository = questionAbstractFactory.createQuestionManagerRepository();
        questionAdminRepository = questionAbstractFactory.createQuestionAdminRepository();
        groupFormationRepository = groupFormationAbstractFactory.createGroupFormationRepositoryInstance();
        groupFormationManager = groupFormationAbstractFactory.createGroupFormationManagerInstance();
    }

    public static Injector instance() {
        if (instance == null) {
            instance = new Injector();
        }
        return instance;
    }

    public IUserAbstractFactory getUserAbstractFactory() {
        return userAbstractFactory;
    }

    public void setUserAbstractFactory(IUserAbstractFactory userAbstractFactory) {
        this.userAbstractFactory = userAbstractFactory;
    }

    public IDatabaseAbstractFactory getDatabaseAbstractFactory() {
        return databaseAbstractFactory;
    }

    public void setDatabaseAbstractFactory(IDatabaseAbstractFactory databaseAbstractFactory) {
        this.databaseAbstractFactory = databaseAbstractFactory;
    }

    public IQuestionAbstractFactory getQuestionAbstractFactory() {
        return questionAbstractFactory;
    }

    public void setQuestionAbstractFactory(IQuestionAbstractFactory questionAbstractFactory) {
        this.questionAbstractFactory = questionAbstractFactory;
    }

    public ISecurityAbstractFactory getSecurityAbstractFactory() {
        return securityAbstractFactory;
    }

    public void setSecurityAbstractFactory(ISecurityAbstractFactory securityAbstractFactory) {
        this.securityAbstractFactory = securityAbstractFactory;
    }

    public ICourseAbstractFactory getCourseAbstractFactory() {
        return courseAbstractFactory;
    }

    public IPasswordAbstractFactory getPasswordAbstractFactory() {
        return passwordAbstractFactory;
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

    public ISurveyAbstractFactory getSurveyAbstractFactory() {
        return surveyAbstractFactory;
    }
    
    public ISurveyRepository getSurveyRepository() {
        return surveyRepository;
    }

    public void setSurveyRepository(ISurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    public IGroupFormationAbstractFactory getGroupFormationAbstractFactory() {
        return groupFormationAbstractFactory;
    }

    public IGroupFormationRepository getGroupFormationRepository() {
        return groupFormationRepository;
    }

    public void setGroupFormationRepository(IGroupFormationRepository groupFormationRepository) {
        this.groupFormationRepository = groupFormationRepository;
    }

    public IGroupFormationManager getGroupFormationManager() {
        return groupFormationManager;
    }

    public void setGroupFormationManager(IGroupFormationManager groupFormationManager) {
        this.groupFormationManager = groupFormationManager;
    }

}
