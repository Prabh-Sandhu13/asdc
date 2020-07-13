package CSCI5308.GroupFormationTool.Survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SurveyController {

    @GetMapping(value = "/survey/createSurvey")
    public String createSurvey(@RequestParam(value = "courseName") String courseName,
                               @RequestParam(value = "courseId") String courseId, Model model) {

        model.addAttribute("courseId", courseId);
        model.addAttribute("courseName", courseName);
        return "survey/createSurvey";
    }

    @PostMapping(value = "/survey/saveSurvey")
    public String saveSurvey(@RequestParam(value = "courseName") String courseName,
                             @RequestParam(value = "courseId") String courseId, Model model) {
        return "redirect:/instructorCourseDetails?courseId=" + courseId + "courseName=" + courseName;
    }

    @PostMapping(value = "/survey/publishSurvey")
    public String publishSurvey(@RequestParam(value = "courseName") String courseName,
                                @RequestParam(value = "courseId") String courseId, Model model) {
        return "redirect:/instructorCourseDetails?courseId=" + courseId + "courseName=" + courseName;
    }
}
