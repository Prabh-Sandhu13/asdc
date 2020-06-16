package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;


public class CourseService implements ICourseService {

	private ICourseRepository courseRepository;

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

	public boolean createCourse(ICourse course) {
		courseRepository = Injector.instance().getCourseRepository();

		return courseRepository.createCourse(course);
	}

	@Override
	public boolean deleteCourse(String courseId) {
		courseRepository = Injector.instance().getCourseRepository();

		return courseRepository.deleteCourse(courseId);
	}

}
