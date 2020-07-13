package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Question.Question;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Course implements ICourse {

    private String id;

    private String name;

    private int credits;

    private String description;

    private ICourseRepository courseRepository;
    
    private static final Logger Log = LoggerFactory.getLogger(Course.class.getName());

    public Course() {
        id = null;
        name = null;
        credits = 0;
        description = null;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getCredits() {
        return credits;
    }

    @Override
    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public ArrayList<ICourse> getAllCourses() {
    	Log.info("Calling the courseRepository function to get all courses");
        courseRepository = Injector.instance().getCourseRepository();
        return courseRepository.getAllCourses();
    }

    @Override
    public ICourse getCourseById(String courseId) {
    	Log.info("Calling the courseRepository function to get courses details by Id");
        courseRepository = Injector.instance().getCourseRepository();
        return courseRepository.getCourseById(courseId);
    }

    public boolean createCourse() {
    	Log.info("Creating a new course and storing it in the database");
        courseRepository = Injector.instance().getCourseRepository();
        return courseRepository.createCourse(this);
    }

    @Override
    public boolean deleteCourse(String courseId) {
    	Log.info("Calling the deleteCourse repository function to delete the course from the Database");
        courseRepository = Injector.instance().getCourseRepository();
        return courseRepository.deleteCourse(courseId);
    }

}
