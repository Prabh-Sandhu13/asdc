package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;

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
}
