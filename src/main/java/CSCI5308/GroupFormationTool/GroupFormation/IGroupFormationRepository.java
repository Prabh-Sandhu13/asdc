package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;
import java.util.TreeMap;

public interface IGroupFormationRepository {

    TreeMap<Integer, ArrayList<IUser>> getGroupsForCourse(String courseId);

}
