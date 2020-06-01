package CSCI5308.GroupFormationTool.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {


	@GetMapping("/admin/allCourses")
	public String adminCourses(Model model) {
		return "admin/allCourses";
	}

	
}
