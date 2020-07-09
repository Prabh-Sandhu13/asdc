package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Course.ICourseAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Mail.IMailManagerAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Password.IPasswordAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactoryTest;
import CSCI5308.GroupFormationTool.Security.ISecurityAbstractFactoryTest;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactoryTest;

public interface IAbstractFactoryTest {

    ICourseAbstractFactoryTest createCourseAbstractFactoryTest();

    IMailManagerAbstractFactoryTest createMailManagerAbstractFactoryTest();

    IPasswordAbstractFactoryTest createPasswordAbstractFactoryTest();

    IQuestionAbstractFactoryTest createQuestionAbstractFactoryTest();

    IUserAbstractFactoryTest createUserAbstractFactoryTest();

    ISecurityAbstractFactoryTest createSecurityAbstractFactoryTest();
}
