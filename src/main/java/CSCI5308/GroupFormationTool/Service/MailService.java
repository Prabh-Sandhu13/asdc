package CSCI5308.GroupFormationTool.Service;

import java.util.List;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.AccessControl.IMailService;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordEncryptor;
import CSCI5308.GroupFormationTool.AccessControl.IPasswordHistoryService;
import CSCI5308.GroupFormationTool.AccessControl.ITokenGenerator;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserRepository;
import CSCI5308.GroupFormationTool.ErrorHandling.UserAlreadyExistsException;
import CSCI5308.GroupFormationTool.Model.StudentCSV;

public class MailService implements IMailService{

	private JavaMailSenderImpl mailSender;
	private SimpleMailMessage msg;
	
	@Override
	public void sendEmail(JavaMailSender javaMailSender, SimpleMailMessage msg) {
		javaMailSender.send(msg);
	}

	@Override
	public JavaMailSenderImpl setupMailSender(JavaMailSenderImpl mailSender) {
		
		mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("noreply.group22@gmail.com");
	    mailSender.setPassword("dalhousiemacs");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	     
	    return mailSender;	
	    }

	@Override
	public boolean sendForgotPasswordMail(IUser userByEmailId, String token) {
		mailSender = setupMailSender(Injector.instance().getJavaMailSender());
		msg = Injector.instance().getMailMessage();
		
		String URL = "http://formgroups22.herokuapp.com/"+"resetPassword"+"?token="+token;
		msg.setTo(userByEmailId.getEmailId());
		msg.setSubject("Complete Password Reset!");
		msg.setFrom("noreply.group22@gmail.com");
		msg.setText("To reset your password, follow this link: "+ URL);
		sendEmail(mailSender, msg);
	
		return true;
	}

	@Override
	@Async
	public boolean sendBatchMail(List<StudentCSV> users, String courseID) {
		
		boolean mailSent = true;
		mailSender = setupMailSender(Injector.instance().getJavaMailSender());
		msg = Injector.instance().getMailMessage();

		msg.setSubject("New Student Registration!");
		msg.setFrom("noreply.group22@gmail.com");

		for (int userCount = 0; userCount < users.size(); userCount++) {
			msg.setTo(users.get(userCount).getEmail());
			msg.setText("Hi,\n\nYou have been added to Group Formation Tool as a student in course " + courseID
					+ ".\n\n" + "Following are your login credentials:\n\nLogin using EmailId: "
					+ users.get(userCount).getEmail() + "\nPassword: " + users.get(userCount).getPassword()
					+"\n\nTo login, go to : http://formgroups22.herokuapp.com/login" +"\n\n\nKind Regards,\nGroup Formation Tool Team-22");
		sendEmail(mailSender, msg);
		}

		return mailSent;
	}

}
