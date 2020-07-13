package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;
import java.util.TreeMap;

import static org.mockito.Mockito.mock;

public class TestGroupFormationAbstractFactory implements ITestGroupFormationAbstractFactory {
    @Override
    public IGroupFormationManager createGroupFormationManagerInstance() {
        return new GroupFormationManager();
    }

    @Override
    public GroupFormationRepository createGroupFormationRepositoryMock() {
        return mock(GroupFormationRepository.class);
    }

    @Override
    public TreeMap<Integer, ArrayList<IUser>> createGroupsInstance() {
        return new TreeMap<Integer, ArrayList<IUser>>();
    }

    @Override
    public GroupFormationManager createGroupFormationMock() {
        return mock(GroupFormationManager.class);
    }
}
