package CSCI5308.GroupFormationTool.Controller;

import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.Injector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

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
