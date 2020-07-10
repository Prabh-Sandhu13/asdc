package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Course.ICourseRepository;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseRepository implements ICourseRepository {

    @Override
    public ArrayList<ICourse> getAllCourses() {

        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ArrayList<ICourse> courseList = new ArrayList<ICourse>();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getAllCourseDetails");
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        ICourse course = new Course();
                        course.setId(results.getString("course_id"));
                        course.setName(results.getString("course_name"));
                        course.setDescription(results.getString("course_details"));
                        course.setCredits(results.getInt("course_credits"));
                        courseList.add(course);
                    }
                }
            }

        } catch (SQLException ex) {

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return courseList;
    }

    @Override
    public boolean createCourse(ICourse course) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        boolean status = true;

        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_createCourse(?,?,?,?,?)");
            storedProcedure.setInputStringParameter(1, course.getId());
            storedProcedure.setInputStringParameter(2, course.getName());
            storedProcedure.setInputIntParameter(3, course.getCredits());
            storedProcedure.setInputStringParameter(4, course.getDescription());
            storedProcedure.registerOutputParameterBoolean(5);
            storedProcedure.execute();
            status = storedProcedure.getParameter(5);

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (null != storedProcedure) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

    @Override
    public boolean deleteCourse(String id) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        boolean status = true;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_deleteACourse(?,?)");
            storedProcedure.setInputStringParameter(1, id);
            storedProcedure.registerOutputParameterBoolean(2);
            storedProcedure.execute();
            status = storedProcedure.getParameter(2);

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (null != storedProcedure) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

    public ICourse getCourseById(String courseId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ICourse course = new Course();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getCourseById(?)");
            storedProcedure.setInputStringParameter(1, courseId);
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        course.setId(results.getString("course_id"));
                        course.setName(results.getString("course_name"));
                        course.setDescription(results.getString("course_details"));
                        course.setCredits(results.getInt("course_credits"));
                    }
                }
            }

        } catch (SQLException ex) {

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return course;
    }

}
