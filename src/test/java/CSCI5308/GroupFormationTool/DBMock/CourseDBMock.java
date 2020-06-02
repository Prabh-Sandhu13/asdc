package CSCI5308.GroupFormationTool.DBMock;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.Model.Course;

public class CourseDBMock implements ICourseRepository {

	private String id;

	private String name;

	private int credits;

	private String description;

	public CourseDBMock() {

		id = "CSCI5308";
		name = "Adv SDC";
		credits = 3;
		description = "sample";

	}

	@Override
	public ArrayList<ICourse> getAllCourses() {

		ArrayList<ICourse> courseList = new ArrayList<>();
		ICourse course = new Course();

		course.setCredits(credits);
		course.setDescription(description);
		course.setName(name);
		course.setId(id);

		courseList.add(course);

		return courseList;
	}

	public ICourse loadCourses(ICourse course) {
		course.setCredits(credits);
		course.setDescription(description);
		course.setId(id);
		course.setName(name);

		return course;
	}

}
