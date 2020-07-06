package CSCI5308.GroupFormationTool.Course;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Course.ICourse;

public class Course implements ICourse {

    private String id;

    private String name;

    private int credits;

    private String description;
    
    private ICourseRepository courseRepository;

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
        courseRepository = Injector.instance().getCourseRepository();
        return courseRepository.getAllCourses();
    }

    @Override
    public ICourse getCourseById(String courseId) {
        courseRepository = Injector.instance().getCourseRepository();
        return courseRepository.getCourseById(courseId);
    }

    public boolean createCourse() {
        courseRepository = Injector.instance().getCourseRepository();
        return courseRepository.createCourse(this);
    }

    @Override
    public boolean deleteCourse(String courseId) {
        courseRepository = Injector.instance().getCourseRepository();
        return courseRepository.deleteCourse(courseId);
    }

}
