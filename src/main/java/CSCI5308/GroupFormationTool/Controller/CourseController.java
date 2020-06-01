package CSCI5308.GroupFormationTool.Controller;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

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

	@GetMapping("/guest/guestCourses")
	public ModelAndView guestCourses(Model model) {

		courseService = Injector.instance().getCourseService();

		ModelAndView modelAndView = null;
		ArrayList<ICourse> courseList = null;
		ArrayList<IUserCourses> userCourseList = null;

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		userService = Injector.instance().getUserService();
		userCoursesService = Injector.instance().getUserCoursesService();

		if (authentication.isAuthenticated()) {
			
			String emailId = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

			if (userService.checkCurrentUserIsAdmin()) {

				courseList = courseService.getAllCourses();
				modelAndView = new ModelAndView("/admin/allCourses");
				modelAndView.addObject("courses", courseList);

			}

			else {

				userCourseList = userCoursesService.getRoleBasedCourses(emailId);

				if (userCourseList.size() == 0) {
					courseList = courseService.getAllCourses();
					modelAndView = new ModelAndView("/guest/guestCourses");
					modelAndView.addObject("courses", courseList);
				}

				else {
					modelAndView = new ModelAndView("/student/studentCourses");
					modelAndView.addObject("courses", courseList);
				}
			}

		}
		return modelAndView;

	}

}
