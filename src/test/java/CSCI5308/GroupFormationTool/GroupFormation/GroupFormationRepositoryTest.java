package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.User.ITestUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.TestUserInjector;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GroupFormationRepositoryTest {

    private ITestGroupFormationAbstractFactory testGroupFormationAbstractFactory = TestGroupFormationInjector.instance().
            getGroupFormationAbstractFactory();

    private ITestUserAbstractFactory testUserAbstractFactory = TestUserInjector.instance().getUserAbstractFactory();

    @Test
    void getGroupsForCourse() {
        TreeMap<Integer, ArrayList<IUser>> groups = testGroupFormationAbstractFactory.createGroupsInstance();
        ArrayList<IUser> userList = testUserAbstractFactory.createUserListInstance();
        assertTrue(groups.size() == 0);
        IUser user = testUserAbstractFactory.createUserInstance();
        user.setFirstName("padmesh");
        user.setLastName("donthu");
        userList.add(user);
        user = testUserAbstractFactory.createUserInstance();
        user.setFirstName("arjun");
        user.setLastName("kh");
        userList.add(user);
        groups.put(1, userList);
        userList = testUserAbstractFactory.createUserListInstance();
        user = testUserAbstractFactory.createUserInstance();
        user.setFirstName("akshay");
        user.setLastName("s");
        userList.add(user);
        groups.put(2, userList);
        assertFalse(groups.isEmpty());
        assertTrue(groups.containsKey(1));
        assertTrue(groups.get(2).size() == 1);
        assertTrue(groups.keySet().size() == 2);
    }
}
