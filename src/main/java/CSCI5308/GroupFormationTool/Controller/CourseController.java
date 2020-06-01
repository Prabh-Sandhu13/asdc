package CSCI5308.GroupFormationTool.Controller;

import java.util.ArrayList;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.AccessControl.IUserCourses;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesService;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;

@Controller
public class CourseController {

	private ICourseService courseService;
	private IUserService userService;
	private IUserCoursesService userCoursesService;

	@GetMapping("/courseList")
	public String guestCourses(Model model) {

		courseService = Injector.instance().getCourseService();

		ArrayList<ICourse> courseList = null;
		ArrayList<IUserCourses> userCourseList = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		userService = Injector.instance().getUserService();
		userCoursesService = Injector.instance().getUserCoursesService();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String emailId = authentication.getPrincipal().toString();

			if (userService.checkCurrentUserIsAdmin(emailId)) {

				courseList = courseService.getAllCourses();
				model.addAttribute("courses", courseList);
				return "/admin/allCourses";

			}

			else {

				userCourseList = userCoursesService.getRoleBasedCourses(emailId);

				if (userCourseList.size() == 0) {
					courseList = courseService.getAllCourses();
					model.addAttribute("courses", courseList);
					return "/guest/guestCourses";
				}

				else {
					model.addAttribute("courses", userCourseList);
					return "/student/studentCourses";
				}
			}

		} else {
			return "/guest/guestCourses";

		}
	}

}
