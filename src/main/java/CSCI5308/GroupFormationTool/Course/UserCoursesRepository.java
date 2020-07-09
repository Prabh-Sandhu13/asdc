package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.Common.FactoryProducer;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserCoursesRepository implements IUserCoursesRepository {

    private IUserAbstractFactory userAbstractFactory = FactoryProducer.
       getFactory().createUserAbstractFactory();
    @Override
    public String getUserRoleByEmailId(String emailId) {
        StoredProcedure storedProcedure = null;
        String role = "Guest";
        try {
            storedProcedure = new StoredProcedure("sp_getUserRoleByEmailId(?)");
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
        ArrayList<ICourse> studentCourseList = new ArrayList<ICourse>();
        try {
            storedProcedure = new StoredProcedure("sp_getStudentCoursesByEmailId(?)");
            storedProcedure.setInputStringParameter(1, emailId);

            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        ICourse course = new Course();
                        course.setId(results.getString("course_id"));
                        course.setName(results.getString("course_name"));
                        course.setDescription(results.getString("course_details"));
                        course.setCredits(results.getInt("course_credits"));
                        studentCourseList.add(course);
                    }
                }
            }
        } catch (SQLException ex) {
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return studentCourseList;
    }

    public ArrayList<IUser> usersCurrentlyNotInstructorsForCourse(String courseId) {
        StoredProcedure storedProcedure = null;
        ArrayList<IUser> userList = userAbstractFactory.createUserListInstance();
        try {
            storedProcedure = new StoredProcedure("sp_getUsersCurrentlyNotInstructorsForCourse(?)");
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
        ArrayList<ICourse> taCourseList = new ArrayList<ICourse>();
        try {
            storedProcedure = new StoredProcedure("sp_getTACoursesByEmailId(?)");
            storedProcedure.setInputStringParameter(1, emailId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        ICourse course = new Course();
                        course.setId(results.getString("course_id"));
                        course.setName(results.getString("course_name"));
                        course.setDescription(results.getString("course_details"));
                        course.setCredits(results.getInt("course_credits"));
                        taCourseList.add(course);
                    }
                }
            }

        } catch (SQLException ex) {

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
        ArrayList<ICourse> instructorCourseList = new ArrayList<ICourse>();
        try {
            storedProcedure = new StoredProcedure("sp_getInstructorCoursesByEmailId(?)");
            storedProcedure.setInputStringParameter(1, emailId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        ICourse course = new Course();
                        course.setId(results.getString("course_id"));
                        course.setName(results.getString("course_name"));
                        course.setDescription(results.getString("course_details"));
                        course.setCredits(results.getInt("course_credits"));
                        instructorCourseList.add(course);
                    }
                }
            }
        } catch (SQLException ex) {

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
        ArrayList<IUser> taList = userAbstractFactory.createUserListInstance();
        try {
            storedProcedure = new StoredProcedure("sp_getTAForCourse(?)");
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
        try {
            storedProcedure = new StoredProcedure("sp_addInstructorsToCourse(?,?)");
            storedProcedure.setInputIntParameter(1, instructor);
            storedProcedure.setInputStringParameter(2, courseId);
            storedProcedure.execute();

        } catch (SQLException ex) {
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
        String emailId = user.getEmailId();
        try {
            storedProcedure = new StoredProcedure("sp_getUserIdByEmailId(?)");
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
            ex.printStackTrace();
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
        try {
            storedProcedure = new StoredProcedure("sp_addTAToCourse(?,?)");
            storedProcedure.setInputStringParameter(1, userId);
            storedProcedure.setInputStringParameter(2, courseId);
            storedProcedure.execute();

        } catch (SQLException ex) {
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
        try {
            storedProcedure = new StoredProcedure("sp_getUserRoleForCourse(?,?)");
            storedProcedure.setInputStringParameter(1, userId);
            storedProcedure.setInputStringParameter(2, courseId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                if (!(results.next())) {
                    return false;
                } else {
                    return true;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
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
        ArrayList<IUser> instructorList = userAbstractFactory.createUserListInstance();
        try {
            storedProcedure = new StoredProcedure("sp_getInstructorsForCourse(?)");
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
                        instructorList.add(user);
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return instructorList;
    }
}
