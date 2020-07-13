package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.TreeMap;

public class GroupFormationManager implements IGroupFormationManager {

    private static final Logger log = LoggerFactory.getLogger(GroupFormationManager.class.getName());
    private IGroupFormationRepository groupFormationRepository;

    @Override
    public TreeMap<Integer, ArrayList<IUser>> getGroupsForCourse(String courseId) {
        log.info("Fetching the groups for the course from the repository");
        groupFormationRepository = Injector.instance().getGroupFormationRepository();
        return groupFormationRepository.getGroupsForCourse(courseId);
    }
}
