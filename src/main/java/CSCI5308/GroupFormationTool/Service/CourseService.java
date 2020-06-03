package CSCI5308.GroupFormationTool.Service;

import java.util.ArrayList;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.AccessControl.IMailService;
import CSCI5308.GroupFormationTool.AccessControl.IUser;

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

	@Override
	@Async
	public boolean sendBatchMail(ArrayList<IUser> users, String courseID, String courseName) {
		
		boolean mailSent = true;
		mailService = Injector.instance().getMailService();
		msg = Injector.instance().getMailMessage();
		jms = mailService.getJavaMailSender();
		
		msg.setSubject("New Student Registration!");
		msg.setFrom("noreply.group22@gmail.com");
		
		for(int userCount = 0; userCount < users.size() ; userCount++) {
			msg.setTo(users.get(userCount).getEmailId());
			msg.setText("Hi,\n\nYou have been added to Group Formation Tool as a student in course "
					+courseID+" : "+courseName+".\n\n"
					+"Following are your login credentials:\n\nLogin EmailId: "
					+users.get(userCount).getEmailId()+"\nPassword: "+users.get(userCount).getPassword()
					+"\n\n\nKind Regards,\nGroup Formation Tool Team-22");
			mailService.sendEmail(jms, msg);
		}
		
		return mailSent;
	}

}
