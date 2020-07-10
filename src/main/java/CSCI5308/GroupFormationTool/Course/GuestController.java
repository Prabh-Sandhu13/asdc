package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.Injector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class GuestController {

    @GetMapping("/guest/guestCourses")
    public String guestCourses(Model model) {
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        ICourse course = courseAbstractFactory.createCourseInstance();
        ArrayList<ICourse> courseList = null;
        courseList = course.getAllCourses();
        model.addAttribute("courses", courseList);
        return "course/guestCourses";
    }
}
