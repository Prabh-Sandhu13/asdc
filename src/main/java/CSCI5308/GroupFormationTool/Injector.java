package CSCI5308.GroupFormationTool;


import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordService;
import CSCI5308.GroupFormationTool.AccessControl.IMailService;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.ITokenGenerator;
import CSCI5308.GroupFormationTool.AccessControl.IUser;

import CSCI5308.GroupFormationTool.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesService;

import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Database.DBConfiguration;
import CSCI5308.GroupFormationTool.Database.IDBConfiguration;
import CSCI5308.GroupFormationTool.Model.User;

import CSCI5308.GroupFormationTool.Repository.ForgotPasswordRepository;
import CSCI5308.GroupFormationTool.Repository.UserRepository;
import CSCI5308.GroupFormationTool.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.Security.TokenGenerator;
import CSCI5308.GroupFormationTool.Service.ForgotPasswordService;
import CSCI5308.GroupFormationTool.Service.MailService;
import CSCI5308.GroupFormationTool.Service.StudentService;
import CSCI5308.GroupFormationTool.Repository.CourseRepository;
import CSCI5308.GroupFormationTool.Repository.UserCoursesRepository;
import CSCI5308.GroupFormationTool.Repository.UserRepository;
import CSCI5308.GroupFormationTool.Security.BCryptEncryption;
import CSCI5308.GroupFormationTool.Service.CourseService;
import CSCI5308.GroupFormationTool.Service.UserCoursesService;
import CSCI5308.GroupFormationTool.AccessControl.IStudentRepository;
import CSCI5308.GroupFormationTool.AccessControl.IStudentService;
import CSCI5308.GroupFormationTool.Repository.StudentRepository;

import CSCI5308.GroupFormationTool.Service.UserService;

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
	private SimpleMailMessage mailMessage;
	private JavaMailSenderImpl mailSender;
	
	private ICourseService courseService;
	private ICourseRepository courseRepository;
	private IUserCoursesRepository userCoursesRepository;
	private IUserCoursesService userCoursesService;	
    private IStudentRepository studentRepository;
    private IStudentService studentService;

	private Injector() {

		dbConfiguration = new DBConfiguration();
		userRepository = new UserRepository();
		userService = new UserService();
		passwordEncryptor = new BCryptEncryption();

		forgotPasswordService = new ForgotPasswordService();
		forgotPasswordRepository = new ForgotPasswordRepository();
		tokenGenerator = new TokenGenerator();
		mailService = new MailService();
		mailMessage = new SimpleMailMessage();
        mailSender = new JavaMailSenderImpl();

		courseService = new CourseService();
		courseRepository = new CourseRepository();
		userCoursesRepository = new UserCoursesRepository();
		userCoursesService = new UserCoursesService();
		studentRepository = new StudentRepository();
		studentService = new StudentService();
	}

	public IUserCoursesRepository getUserCoursesRepository() {
		return userCoursesRepository;
	}

	public IUserCoursesService getUserCoursesService() {
		return userCoursesService;

	}

	public static Injector instance() {

		if (instance == null) {
			instance = new Injector();
		}
		return instance;
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
	
	public IForgotPasswordService getForgotPasswordService(){
		return forgotPasswordService;
	}
	
	public IForgotPasswordRepository getForgotPasswordRepository(){
		return forgotPasswordRepository;
	}
	
	public ITokenGenerator getTokenGenerator(){
		return tokenGenerator;
	}


	public IMailService getMailService(){
		return mailService;
	}
	
	public SimpleMailMessage getMailMessage(){
		return mailMessage;
	}
	
	public JavaMailSenderImpl getJavaMailSender(){
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
}
