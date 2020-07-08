package CSCI5308.GroupFormationTool.Common;

import CSCI5308.GroupFormationTool.Course.CourseAbstractFactory;
import CSCI5308.GroupFormationTool.Course.ICourseAbstractFactory;
import CSCI5308.GroupFormationTool.Password.IPasswordAbstractFactory;
import CSCI5308.GroupFormationTool.Password.PasswordAbstractFactory;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Question.QuestionAbstractFactory;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.UserAbstractFactory;

public class AbstractFactory implements IAbstractFactory{

    @Override
    public ICourseAbstractFactory createCourseAbstractFactory() {
        return new CourseAbstractFactory();
    }

    @Override
    public IPasswordAbstractFactory createPasswordAbstractFactory() {
        return new PasswordAbstractFactory();
    }

    @Override
    public IQuestionAbstractFactory createQuestionAbstractFactory() {
        return new QuestionAbstractFactory();
    }

    @Override
    public IUserAbstractFactory createUserAbstractFactory() {
        return new UserAbstractFactory();
    }
}
