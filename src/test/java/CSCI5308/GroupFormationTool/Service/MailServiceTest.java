package CSCI5308.GroupFormationTool.Service;

import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.Model.User;
import org.junit.jupiter.api.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class MailServiceTest {

    public MailService mailService;
    public JavaMailSenderImpl javaMailSender;
    public SimpleMailMessage mailMessage;

    @Test
    void setupMailSenderTest() {
        mailService = new MailService();
        javaMailSender = new JavaMailSenderImpl();
        mailMessage = new SimpleMailMessage();
        assertTrue(mailService.setupMailSender(javaMailSender) instanceof JavaMailSenderImpl);
        assertTrue(mailService.setupMailSender(javaMailSender).getHost().equals(DomainConstants.smtpHost));
        assertFalse(mailService.setupMailSender(javaMailSender).getPassword().isEmpty());
    }

    @Test
    void sendForgotPasswordMailTest() {
        mailService = mock(MailService.class);
        User user = new User();
        user.setId(123);
        user.setBannerId("B00827531");
        user.setEmailId("haard.shah@dal.ca");
        user.setFirstName("haard");
        user.setLastName("shah");
        user.setPassword("pswd12345");
        user.setConfirmPassword("pswd12345");

        String token = "sample token";

        // Doing this since sendMail method of JavaMailSenderImpl object cannot be mocked.
        when(mailService.sendForgotPasswordMail(user, token)).thenReturn(true);
        assertTrue(mailService.sendForgotPasswordMail(user, token));
    }

    @Test
    void sendBatchMailTest() {
        mailService = mock(MailService.class);
        ArrayList<StudentCSV> studentCSVList = new ArrayList<>();
        StudentCSV studentCSV = new StudentCSV();

        studentCSV.setFirstName("Padmesh");
        studentCSV.setLastName("Donthu");
        studentCSV.setBannerId("B00854462");
        studentCSV.setEmail("padmeshdonthu@gmail.com");
        studentCSVList.add(studentCSV);

        studentCSV = new StudentCSV();
        studentCSV.setFirstName("Padmesh");
        studentCSV.setLastName("Kumar");
        studentCSV.setBannerId("B00854461");
        studentCSV.setEmail("padmeshd1996@gmail.com");
        studentCSVList.add(studentCSV);

        when(mailService.sendBatchMail(studentCSVList, "CSCI 5308")).thenReturn(true);
        assertTrue(mailService.sendBatchMail(studentCSVList, "CSCI 5308"));
    }
}
