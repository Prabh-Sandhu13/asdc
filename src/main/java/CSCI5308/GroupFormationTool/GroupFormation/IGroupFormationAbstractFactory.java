package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;
import java.util.TreeMap;

public interface IGroupFormationAbstractFactory {

    IGroup createGroupInstance();

    ArrayList<IGroup> createGroupListInstance();

    IGroupFormationManager createGroupFormationManagerInstance();

    GroupFormationRepository createGroupFormationRepositoryInstance();

    TreeMap<Integer, ArrayList<IUser>> createGroupsForCourseInstance();

}
