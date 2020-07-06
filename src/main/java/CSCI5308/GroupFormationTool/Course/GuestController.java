package CSCI5308.GroupFormationTool.Course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class GuestController {

    @GetMapping("/guest/guestCourses")
    public String guestCourses(Model model) {
        ICourse course = new Course();
        ArrayList<ICourse> courseList = null;
        courseList = course.getAllCourses();
        model.addAttribute("courses", courseList);
        return "course/guestCourses";
    }
}
