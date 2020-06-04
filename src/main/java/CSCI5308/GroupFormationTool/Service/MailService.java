package CSCI5308.GroupFormationTool.Service;

import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.IMailService;

public class MailService implements IMailService{

	private JavaMailSenderImpl jms;
	
	
	@Override
	public void sendEmail(JavaMailSender javaMailSender, SimpleMailMessage msg) {
		javaMailSender.send(msg);
	}

	@Override
	public JavaMailSender getJavaMailSender() {
		jms = Injector.instance().getJavaMailSender();
		jms.setHost("smtp.gmail.com");
	    jms.setPort(587);
	     
	    jms.setUsername("noreply.group22@gmail.com");
	    jms.setPassword("dalhousiemacs");
	     
	    Properties props = jms.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	     
	    return jms;	
	    }

}
