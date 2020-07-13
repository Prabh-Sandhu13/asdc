package CSCI5308.GroupFormationTool;

import CSCI5308.GroupFormationTool.Course.ITestCourseAbstractFactory;
import CSCI5308.GroupFormationTool.Course.TestCourseAbstractFactory;
import CSCI5308.GroupFormationTool.GroupFormation.ITestGroupFormationAbstractFactory;
import CSCI5308.GroupFormationTool.GroupFormation.TestGroupFormationAbstractFactory;
import CSCI5308.GroupFormationTool.Mail.ITestMailManagerAbstractFactory;
import CSCI5308.GroupFormationTool.Mail.TestMailManagerAbstractFactory;
import CSCI5308.GroupFormationTool.Password.ITestPasswordAbstractFactory;
import CSCI5308.GroupFormationTool.Password.TestPasswordAbstractFactory;
import CSCI5308.GroupFormationTool.Question.ITestQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Question.TestQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Security.ITestSecurityAbstractFactory;
import CSCI5308.GroupFormationTool.Security.TestSecurityAbstractFactory;
import CSCI5308.GroupFormationTool.User.ITestUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.TestUserAbstractFactory;

public class TestsInjector {

    private static TestsInjector instance = null;
    private ITestCourseAbstractFactory courseAbstractFactoryTest;
    private ITestQuestionAbstractFactory questionAbstractFactoryTest;
    private ITestUserAbstractFactory userAbstractFactoryTest;
    private ITestSecurityAbstractFactory securityAbstractFactoryTest;
    private ITestPasswordAbstractFactory passwordAbstractFactoryTest;
    private ITestMailManagerAbstractFactory mailManagerAbstractFactoryTest;
    private ITestGroupFormationAbstractFactory groupFormationAbstractFactoryTest;

    private TestsInjector() {
        courseAbstractFactoryTest = new TestCourseAbstractFactory();
        questionAbstractFactoryTest = new TestQuestionAbstractFactory();
        userAbstractFactoryTest = new TestUserAbstractFactory();
        securityAbstractFactoryTest = new TestSecurityAbstractFactory();
        passwordAbstractFactoryTest = new TestPasswordAbstractFactory();
        mailManagerAbstractFactoryTest = new TestMailManagerAbstractFactory();
        groupFormationAbstractFactoryTest = new TestGroupFormationAbstractFactory();
    }

    public static TestsInjector instance() {

        if (instance == null) {
            return new TestsInjector();
        }
        return instance;
    }

    public ITestGroupFormationAbstractFactory getGroupFormationAbstractFactoryTest() {
        return groupFormationAbstractFactoryTest;
    }

    public void setGroupFormationAbstractFactoryTest(ITestGroupFormationAbstractFactory
                                                             groupFormationAbstractFactoryTest) {
        this.groupFormationAbstractFactoryTest = groupFormationAbstractFactoryTest;
    }

    public ITestCourseAbstractFactory getCourseAbstractFactoryTest() {
        return courseAbstractFactoryTest;
    }

    public ITestQuestionAbstractFactory getQuestionAbstractFactoryTest() {
        return questionAbstractFactoryTest;
    }

    public ITestUserAbstractFactory getUserAbstractFactoryTest() {
        return userAbstractFactoryTest;
    }

    public ITestSecurityAbstractFactory getSecurityAbstractFactoryTest() {
        return securityAbstractFactoryTest;
    }

    public ITestPasswordAbstractFactory getPasswordAbstractFactoryTest() {
        return passwordAbstractFactoryTest;
    }

    public ITestMailManagerAbstractFactory getMailManagerAbstractFactoryTest() {
        return mailManagerAbstractFactoryTest;
    }

}
