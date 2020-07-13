package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;
import java.util.TreeMap;

public interface ITestGroupFormationAbstractFactory {

    IGroupFormationManager createGroupFormationManagerInstance();

    GroupFormationRepository createGroupFormationRepositoryMock();

    TreeMap<Integer, ArrayList<IUser>> createGroupsInstance();

    GroupFormationManager createGroupFormationMock();

}
