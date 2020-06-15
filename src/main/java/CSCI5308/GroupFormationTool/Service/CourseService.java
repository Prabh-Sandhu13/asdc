package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.AccessControl.IMailService;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.Model.Course;

public class CourseService implements ICourseService {

	private ICourseRepository courseRepository;
	private IMailService mailService;
	private SimpleMailMessage msg;
	private JavaMailSender jms;

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
