package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Course.CourseAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Course.ICourseAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Mail.IMailManagerAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Mail.MailManagerAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Password.IPasswordAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Password.PasswordAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Question.QuestionAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Security.ISecurityAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Security.SecurityAbstractFactoryTest;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactoryTest;
import CSCI5308.GroupFormationTool.User.UserAbstractFactoryTest;

public class TestsInjector {

    private static TestsInjector instance = null;
    private ICourseAbstractFactoryTest courseAbstractFactoryTest;
    private IQuestionAbstractFactoryTest questionAbstractFactoryTest;
    private IUserAbstractFactoryTest userAbstractFactoryTest;
    private ISecurityAbstractFactoryTest securityAbstractFactoryTest;
    private IPasswordAbstractFactoryTest passwordAbstractFactoryTest;
    private IMailManagerAbstractFactoryTest mailManagerAbstractFactoryTest;

    private TestsInjector() {
        courseAbstractFactoryTest = new CourseAbstractFactoryTest();
        questionAbstractFactoryTest = new QuestionAbstractFactoryTest();
        userAbstractFactoryTest = new UserAbstractFactoryTest();
        securityAbstractFactoryTest = new SecurityAbstractFactoryTest();
        passwordAbstractFactoryTest = new PasswordAbstractFactoryTest();
        mailManagerAbstractFactoryTest = new MailManagerAbstractFactoryTest();
    }

    public static TestsInjector instance() {

        if (instance == null) {
            return new TestsInjector();
        }
        return instance;
    }

    public ICourseAbstractFactoryTest getCourseAbstractFactoryTest() {
        return courseAbstractFactoryTest;
    }

    public IQuestionAbstractFactoryTest getQuestionAbstractFactoryTest() {
        return questionAbstractFactoryTest;
    }

    public IUserAbstractFactoryTest getUserAbstractFactoryTest() {
        return userAbstractFactoryTest;
    }

    public ISecurityAbstractFactoryTest getSecurityAbstractFactoryTest() {
        return securityAbstractFactoryTest;
    }

    public IPasswordAbstractFactoryTest getPasswordAbstractFactoryTest() {
        return passwordAbstractFactoryTest;
    }

    public IMailManagerAbstractFactoryTest getMailManagerAbstractFactoryTest() {
        return mailManagerAbstractFactoryTest;
    }

}
