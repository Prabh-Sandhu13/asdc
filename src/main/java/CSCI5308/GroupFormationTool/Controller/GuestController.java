package CSCI5308.GroupFormationTool.Controller;

import java.util.ArrayList;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesService;

@Controller
public class GuestController {

	IUserCoursesService userCoursesService;
	ICourseService courseService;

	@GetMapping("/guest/guestCourses")
	public String guestCourses(Model model) {

		userCoursesService = Injector.instance().getUserCoursesService();
		courseService = Injector.instance().getCourseService();
		ArrayList<ICourse> courseList = null;

		String emailId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

		if (userCoursesService.getRoleBasedCourses(emailId).size() > 0) {

			model.addAttribute("authorizationError", "You are not authorized to use this page!!");
			return "guest/guestCourses";
		} else {
			courseList = courseService.getAllCourses();
			model.addAttribute("courses", courseList);
			return "guest/guestCourses";
		}
	}

}
