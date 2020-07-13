package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.TreeMap;

public class GroupFormationRepository implements IGroupFormationRepository {

    private static final Logger log = LoggerFactory.getLogger(GroupFormationRepository.class.getName());

    @Override
    public TreeMap<Integer, ArrayList<IUser>> getGroupsForCourse(String courseId) {
        IGroupFormationAbstractFactory groupFormationAbstractFactory = Injector.instance().
                getGroupFormationAbstractFactory();
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        TreeMap<Integer, ArrayList<IUser>> groups = groupFormationAbstractFactory.createGroupsForCourseInstance();
        ArrayList<IUser> userList = userAbstractFactory.createUserListInstance();
        StoredProcedure storedProcedure = null;
        try {
            log.info("Calling stored procedure sp_getGroupsForCourse to fetch the groups for the course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getGroupsForCourse(?)");
            storedProcedure.setInputStringParameter(1, courseId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    int groupNumber = results.getInt("groupNumber");
                    IUser user = userAbstractFactory.createUserInstance();
                    user.setBannerId(results.getString("bannerId"));
                    user.setFirstName(results.getString("firstName"));
                    user.setLastName(results.getString("lastName"));
                    user.setEmailId(results.getString("emailId"));
                    if (groups.containsKey(groupNumber)) {
                        userList = groups.get(groupNumber);
                    } else {
                        userList = userAbstractFactory.createUserListInstance();
                    }
                    userList.add(user);
                    groups.put(groupNumber, userList);
                }
            }
        } catch (SQLException exception) {
            log.error("Could not execute the Stored procedure sp_getGroupsForCourse" +
                    " because of an SQL Exception " + exception.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return groups;
    }
}
