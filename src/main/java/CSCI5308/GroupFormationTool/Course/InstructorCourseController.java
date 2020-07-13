package CSCI5308.GroupFormationTool.Course;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class InstructorCourseController {

    @GetMapping(value = "/instructorCourseDetails")
    public String courseDetails(@RequestParam(value = "courseName") String courseName,
                                @RequestParam(value = "courseId") String courseId, Model model) {

        model.addAttribute("courseId", courseId);
        model.addAttribute("courseName", courseName);
        return "course/instructorCourseDetails";
    }
}
