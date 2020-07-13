package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;
import java.util.TreeMap;

public class GroupFormationAbstractFactory implements IGroupFormationAbstractFactory {

    @Override
    public IGroup createGroupInstance() {
        return new Group();
    }

    @Override
    public ArrayList<IGroup> createGroupListInstance() {
        return new ArrayList<IGroup>();
    }

    @Override
    public IGroupFormationManager createGroupFormationManagerInstance() {
        return new GroupFormationManager();
    }

    @Override
    public GroupFormationRepository createGroupFormationRepositoryInstance() {
        return new GroupFormationRepository();
    }

    @Override
    public TreeMap<Integer, ArrayList<IUser>> createGroupsForCourseInstance() {
        return new TreeMap<Integer, ArrayList<IUser>>();
    }
}
