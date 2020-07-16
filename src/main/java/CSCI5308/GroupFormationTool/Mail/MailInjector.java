package CSCI5308.GroupFormationTool.Mail;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

public class MailInjector {

    private static MailInjector instance = null;

    private IMailAbstractFactory mailAbstractFactory;

    private IMailManager mailManager;

    private SimpleMailMessage mailMessage;

    private JavaMailSenderImpl mailSender;

    private MailInjector() {
        mailAbstractFactory = new MailAbstractFactory();
        mailManager = mailAbstractFactory.createMailManagerInstance();
        mailMessage = mailAbstractFactory.createSimpleMailMessageInstance();
        mailSender = mailAbstractFactory.createJavaMailSenderInstance();
    }

    public static MailInjector instance() {
        if (instance == null) {
            instance = new MailInjector();
        }
        return instance;
    }

    public IMailAbstractFactory getMailAbstractFactory() {
        return mailAbstractFactory;
    }

    public void setMailAbstractFactory(IMailAbstractFactory mailAbstractFactory) {
        this.mailAbstractFactory = mailAbstractFactory;
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

    public void setMailMessage(SimpleMailMessage mailMessage) {
        this.mailMessage = mailMessage;
    }

    public JavaMailSenderImpl getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

}
