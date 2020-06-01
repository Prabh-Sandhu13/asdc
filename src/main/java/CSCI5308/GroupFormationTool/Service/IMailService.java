package CSCI5308.GroupFormationTool.Service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public interface IMailService {
	public void sendEmail(JavaMailSender javaMailSender, SimpleMailMessage msg);
	public JavaMailSender getJavaMailSender();
}
