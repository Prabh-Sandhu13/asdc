package CSCI5308.GroupFormationTool;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordService;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.ITokenGenerator;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
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
import CSCI5308.GroupFormationTool.Service.IMailService;
import CSCI5308.GroupFormationTool.Service.MailService;
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
	private SimpleMailMessage msg;
	private JavaMailSenderImpl jms;
	
	
	private Injector() {

		dbConfiguration = new DBConfiguration();
		userRepository = new UserRepository();
		userService = new UserService();
		passwordEncryptor = new BCryptEncryption();
		forgotPasswordService = new ForgotPasswordService();
		forgotPasswordRepository = new ForgotPasswordRepository();
		tokenGenerator = new TokenGenerator();
		mailService = new MailService();
		msg = new SimpleMailMessage();
        jms = new JavaMailSenderImpl();
	}

	public static Injector instance() {

		if(instance==null) {
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
		return msg;
	}
	
	public JavaMailSenderImpl getJavaMailSender(){
		return jms;
	}
	
}
