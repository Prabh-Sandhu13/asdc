package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.AccessControl.IMailService;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.Properties;

public class MailService implements IMailService {

    private JavaMailSenderImpl mailSender;
    private SimpleMailMessage msg;

    @Override
    public void sendEmail(JavaMailSender javaMailSender, SimpleMailMessage msg) {
        javaMailSender.send(msg);
    }

    @Override
    public JavaMailSenderImpl setupMailSender(JavaMailSenderImpl mailSender) {

        mailSender.setHost(DomainConstants.smtpHost);
        mailSender.setPort(DomainConstants.smtpPort);

        mailSender.setUsername(DomainConstants.mailUserName);
        mailSender.setPassword(DomainConstants.mailPassword);

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

        String URL = DomainConstants.domainUrl + "/resetPassword?token=" + token;

        msg.setTo(userByEmailId.getEmailId());
        msg.setSubject(DomainConstants.forgotPasswordSubject);
        msg.setFrom(DomainConstants.mailUserName);
        msg.setText(DomainConstants.forgotPasswordText + URL);

        sendEmail(mailSender, msg);

        return true;
    }

    @Override
    @Async
    public boolean sendBatchMail(List<StudentCSV> users, String courseID) {

		mailSender = setupMailSender(Injector.instance().getJavaMailSender());
        msg = Injector.instance().getMailMessage();

        msg.setSubject(DomainConstants.registrationSubject);
        msg.setFrom(DomainConstants.mailUserName);

        for (int userCount = 0; userCount < users.size(); userCount++) {

            msg.setTo(users.get(userCount).getEmail());
            msg.setText("Hi,\n\nYou have been added to Group Formation Tool as a student in course " + courseID
                    + ".\n\n" + "Following are your login credentials:\n\nLogin using EmailId: "
                    + users.get(userCount).getEmail() + "\nPassword: " + users.get(userCount).getPassword()
                    + "\n\nTo login, go to : " + DomainConstants.domainUrl + "/login"
                    + "\n\n\nKind Regards,\nGroup Formation Tool Team-22");

            sendEmail(mailSender, msg);
        }

        return true;
    }

}
