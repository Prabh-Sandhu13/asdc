package CSCI5308.GroupFormationTool.Common;

import CSCI5308.GroupFormationTool.Course.ICourseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Password.IPasswordAbstractFactory;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactory;
import CSCI5308.GroupFormationTool.Security.ISecurityAbstractFactory;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;

public interface IAbstractFactory {

    ICourseAbstractFactory createCourseAbstractFactory();

    IPasswordAbstractFactory createPasswordAbstractFactory();

    IQuestionAbstractFactory createQuestionAbstractFactory();

    IUserAbstractFactory createUserAbstractFactory();

    ISecurityAbstractFactory createSecurityAbstractFactory();

    IDatabaseAbstractFactory createDatabaseAbstractFactory();
}
