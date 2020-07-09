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

public class AbstractFactoryTest implements IAbstractFactoryTest{
    @Override
    public ICourseAbstractFactoryTest createCourseAbstractFactoryTest() {
        return new CourseAbstractFactoryTest();
    }

    @Override
    public IMailManagerAbstractFactoryTest createMailManagerAbstractFactoryTest() {
        return new MailManagerAbstractFactoryTest();
    }

    @Override
    public IPasswordAbstractFactoryTest createPasswordAbstractFactoryTest() {
        return new PasswordAbstractFactoryTest();
    }

    @Override
    public IQuestionAbstractFactoryTest createQuestionAbstractFactoryTest() {
        return new QuestionAbstractFactoryTest();
    }

    @Override
    public IUserAbstractFactoryTest createUserAbstractFactoryTest() {
        return new UserAbstractFactoryTest();
    }

    @Override
    public ISecurityAbstractFactoryTest createSecurityAbstractFactoryTest() {
        return new SecurityAbstractFactoryTest();
    }
}
