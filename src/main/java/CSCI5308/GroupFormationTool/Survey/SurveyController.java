package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Course.InstructorCourseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SurveyController {

    private static final Logger log = LoggerFactory.getLogger(InstructorCourseController.class.getName());

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
    public ModelAndView publishSurvey(@RequestParam(value = "courseName") String courseName,
                                      @RequestParam(value = "courseId") String courseId, Model model) {
        ISurveyAbstractFactory surveyAbstractFactory = SurveyInjector.instance().getSurveyAbstractFactory();
        ISurvey survey = surveyAbstractFactory.createSurveyInstance();
        ModelAndView modelAndView;
        log.info("Getting survey id for the course");
        int surveyId = survey.getSurveyIdByCourseId(courseId);
        log.info("Checking if the survey is published");
        boolean outcome = survey.publishSurvey(courseId);
        modelAndView = new ModelAndView("course/instructorCourseDetails");
        if (outcome) {
            modelAndView.addObject("published", true);
        } else {
            modelAndView.addObject("published", false);
            modelAndView.addObject("questionError", "Add atleast one question to the " +
                    "survey to publish the survey!!");
        }
        modelAndView.addObject("courseId", courseId);
        modelAndView.addObject("courseName", courseName);
        modelAndView.addObject("created", surveyId);
        return modelAndView;
    }
}
