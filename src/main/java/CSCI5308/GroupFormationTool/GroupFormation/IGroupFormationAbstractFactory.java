package CSCI5308.GroupFormationTool.GroupFormation;

import java.util.ArrayList;

public interface IGroupFormationAbstractFactory {

    IGroup createGroupInstance();

    ArrayList<IGroup> createGroupListInstance();

    IGroupFormationManager createGroupFormationManagerInstance();

    GroupFormationRepository createGroupFormationRepositoryInstance();

}
