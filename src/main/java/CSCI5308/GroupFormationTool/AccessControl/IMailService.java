package CSCI5308.GroupFormationTool.AccessControl;

import CSCI5308.GroupFormationTool.Model.StudentCSV;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.List;

public interface IMailService {

	void sendEmail(JavaMailSender javaMailSender, SimpleMailMessage msg);

    boolean sendForgotPasswordMail(IUser user, String token);

    boolean sendBatchMail(List<StudentCSV> list, String courseID);

    JavaMailSenderImpl setupMailSender(JavaMailSenderImpl javaMailSender);
}
