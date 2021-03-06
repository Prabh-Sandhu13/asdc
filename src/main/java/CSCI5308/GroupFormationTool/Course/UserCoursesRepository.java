package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Question.QuestionManagerRepository;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserCoursesRepository implements IUserCoursesRepository {

	private static final Logger Log = LoggerFactory.getLogger(QuestionManagerRepository.class.getName());
	
    @Override
    public String getUserRoleByEmailId(String emailId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        String role = "Guest";
        try {
        	Log.info("Calling stored procedure sp_getUserRoleByEmailId to get user role by Email Id");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getUserRoleByEmailId(?)");
            storedProcedure.setInputStringParameter(1, emailId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        String roleRet = results.getString("role_type");
                        role = roleRet;
                        if (roleRet.equals("Instructor")) {
                            break;
                        }
                        if (roleRet.equals("TA")) {
                            break;
                        }
                    }
                }
            }
        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getUserRoleByEmailId" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return role;
    }

    @Override
    public ArrayList<ICourse> getStudentCourses(String emailId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        ArrayList<ICourse> studentCourseList = courseAbstractFactory.createCourseListInstance();
        try {
        	Log.info("Calling stored procedure sp_getStudentCoursesByEmailId to get student courses by Email Id");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getStudentCoursesByEmailId(?)");
            storedProcedure.setInputStringParameter(1, emailId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        ICourse course = courseAbstractFactory.createCourseInstance();
                        course.setId(results.getString("course_id"));
                        course.setName(results.getString("course_name"));
                        course.setDescription(results.getString("course_details"));
                        course.setCredits(results.getInt("course_credits"));
                        studentCourseList.add(course);
                    }
                }
            }
        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getStudentCoursesByEmailId" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return studentCourseList;
    }

    public ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        ArrayList<IUser> userList = userAbstractFactory.createUserListInstance();
        try {
        	Log.info("Calling stored procedure sp_getUsersCurrentlyNotInstructorsForCourse to get users that are not instrcutors for the course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getUsersCurrentlyNotInstructorsForCourse(?)");
            storedProcedure.setInputStringParameter(1, courseId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        IUser user = userAbstractFactory.createUserInstance();
                        user.setBannerId(results.getString("banner_id"));
                        user.setEmailId(results.getString("email"));
                        user.setFirstName(results.getString("first_name"));
                        user.setLastName(results.getString("last_name"));
                        user.setId(results.getLong("user_id"));
                        userList.add(user);
                    }
                }
            }
        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getUsersCurrentlyNotInstructorsForCourse" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return userList;
    }

    @Override
    public ArrayList<ICourse> getTACourses(String emailId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        ArrayList<ICourse> taCourseList = courseAbstractFactory.createCourseListInstance();
        try {
        	Log.info("Calling stored procedure sp_getTACoursesByEmailId to get courses of a TA using Email Id");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getTACoursesByEmailId(?)");
            storedProcedure.setInputStringParameter(1, emailId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        ICourse course = courseAbstractFactory.createCourseInstance();
                        course.setId(results.getString("course_id"));
                        course.setName(results.getString("course_name"));
                        course.setDescription(results.getString("course_details"));
                        course.setCredits(results.getInt("course_credits"));
                        taCourseList.add(course);
                    }
                }
            }
        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getTACoursesByEmailId" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return taCourseList;
    }

    @Override
    public ArrayList<ICourse> getInstructorCourses(String emailId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        ArrayList<ICourse> instructorCourseList = courseAbstractFactory.createCourseListInstance();
        try {
        	Log.info("Calling stored procedure sp_getInstructorCoursesByEmailId to get courses of a Instructor using Email Id");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getInstructorCoursesByEmailId(?)");
            storedProcedure.setInputStringParameter(1, emailId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        ICourse course = courseAbstractFactory.createCourseInstance();
                        course.setId(results.getString("course_id"));
                        course.setName(results.getString("course_name"));
                        course.setDescription(results.getString("course_details"));
                        course.setCredits(results.getInt("course_credits"));
                        instructorCourseList.add(course);
                    }
                }
            }
        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getInstructorCoursesByEmailId" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return instructorCourseList;
    }

    @Override
    public ArrayList<IUser> getTAForCourse(String courseId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        ArrayList<IUser> taList = userAbstractFactory.createUserListInstance();
        try {
        	Log.info("Calling stored procedure sp_getTAForCourse to get TA for a course using course Id");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getTAForCourse(?)");
            storedProcedure.setInputStringParameter(1, courseId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        IUser user = userAbstractFactory.createUserInstance();
                        user.setBannerId(results.getString("banner_id"));
                        user.setEmailId(results.getString("email"));
                        user.setFirstName(results.getString("first_name"));
                        user.setLastName(results.getString("last_name"));
                        user.setId(results.getLong("user_id"));
                        taList.add(user);
                    }
                }
            }
        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getTAForCourse" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return taList;
    }

    @Override
    public boolean addInstructorsToCourse(Long instructor, String courseId) {

        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        try {
        	Log.info("Calling stored procedure sp_addInstructorsToCourse to add instructor to a Course using Course Id");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_addInstructorsToCourse(?,?)");
            storedProcedure.setInputIntParameter(1, instructor);
            storedProcedure.setInputStringParameter(2, courseId);
            storedProcedure.execute();

        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_addInstructorsToCourse" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return true;
    }

    @Override
    public boolean enrollTAForCourseUsingEmailId(IUser user, String courseId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        String emailId = user.getEmailId();
        try {
        	Log.info("Calling stored procedure sp_getUserIdByEmailId to get a user by Email Id");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getUserIdByEmailId(?)");
            storedProcedure.setInputStringParameter(1, emailId);

            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                if (!(results.next())) {
                    return false;
                } else {
                    String userId = results.getString("user_id");
                    Boolean roleExists = getUserRoleForCourse(userId, courseId);
                    if (roleExists) {
                        return false;
                    } else {
                        return assignUserAsTA(userId, courseId);
                    }
                }
            }

        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getUserIdByEmailId" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return true;
    }

    private boolean assignUserAsTA(String userId, String courseId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        try {
        	Log.info("Calling stored procedure sp_addTAToCourse to add TA to a Course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_addTAToCourse(?,?)");
            storedProcedure.setInputStringParameter(1, userId);
            storedProcedure.setInputStringParameter(2, courseId);
            storedProcedure.execute();

        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_addTAToCourse" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return true;
    }

    @Override
    public boolean getUserRoleForCourse(String userId, String courseId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        try {
        	Log.info("Calling stored procedure sp_getUserRoleForCourse to get a user role for the Course by User Id");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getUserRoleForCourse(?,?)");
            storedProcedure.setInputStringParameter(1, userId);
            storedProcedure.setInputStringParameter(2, courseId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                return results.next();
            }

        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getUserRoleForCourse" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return true;
    }

    @Override
    public ArrayList<IUser> getInstructorsForCourse(String courseId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        ArrayList<IUser> instructorList = userAbstractFactory.createUserListInstance();
        try {
        	Log.info("Calling stored procedure sp_getInstructorsForCourse to get instructor for a Course");
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getInstructorsForCourse(?)");
            storedProcedure.setInputStringParameter(1, courseId);

            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        IUser user = userAbstractFactory.createUserInstance();
                        user.setFirstName(results.getString("first_name"));
                        user.setLastName(results.getString("last_name"));
                        user.setEmailId(results.getString("email"));
                        user.setBannerId(results.getString("banner_id"));
                        user.setId(results.getLong("user_id"));
                        instructorList.add(user);
                    }
                }
            }
        } catch (SQLException ex) {
        	Log.error("Could not execute the Stored procedure sp_getInstructorsForCourse" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return instructorList;
    }
}
