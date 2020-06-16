package CSCI5308.GroupFormationTool.AccessControl;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import CSCI5308.GroupFormationTool.Model.StudentCSV;

public interface IMailService {
	public void sendEmail(JavaMailSender javaMailSender, SimpleMailMessage msg);
	public boolean sendForgotPasswordMail(IUser user, String token);
	public boolean sendBatchMail(List<StudentCSV> list, String courseID);
	public JavaMailSenderImpl setupMailSender(JavaMailSenderImpl javaMailSender);
}
