package CSCI5308.GroupFormationTool.Controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;

@Controller
public class GuestController {

	ICourseService courseService;

	@GetMapping("/guest/guestCourses")
	public String guestCourses(Model model) {

		courseService = Injector.instance().getCourseService();
		ArrayList<ICourse> courseList = null;
		
		courseList = courseService.getAllCourses();
		model.addAttribute("courses", courseList);
		
		return "guest/guestCourses";
	}
}
