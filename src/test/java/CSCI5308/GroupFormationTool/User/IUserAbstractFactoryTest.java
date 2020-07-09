package CSCI5308.GroupFormationTool.User;

import java.util.ArrayList;

public interface IUserAbstractFactoryTest {

    IUser createUserInstance();

    ArrayList<IUser> createUserListInstance();

    UserDBMock createUserDBMock();

    UserRepository createUserRepositoryMock();

}

