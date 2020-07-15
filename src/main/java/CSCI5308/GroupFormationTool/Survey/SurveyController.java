package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactory;

@Controller
public class SurveyController {

    @GetMapping(value = "/survey/createSurvey")
    public String createSurvey(@RequestParam(value = "courseName") String courseName,
                               @RequestParam(value = "courseId") String courseId, Model model) {

        model.addAttribute("courseId", courseId);
        model.addAttribute("courseName", courseName);
        ISurveyAbstractFactory surveyAbstractFactory = Injector.instance().getSurveyAbstractFactory();
        ISurvey survey = surveyAbstractFactory.createSurveyInstance();
        int surveyId = survey.createSurvey(courseId);
        ArrayList<IQuestion> surveyQuestionList = survey.getQuestionsForSurvey(courseId);
        model.addAttribute("questionList", null);
        model.addAttribute("surveyQuestionList", surveyQuestionList);
        model.addAttribute("surveyId", surveyId);
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
    
    @GetMapping("/survey/addQuestionToSurvey")
    public String addQuestionToSurvey(@RequestParam("questionId") long questionId, @RequestParam("surveyId") long surveyId, @RequestParam("courseId") String courseId, @RequestParam("courseName") String courseName, Model model) {

        boolean status;
        ISurveyAbstractFactory surveyAbstractFactory = Injector.instance().getSurveyAbstractFactory();
        ISurvey survey = surveyAbstractFactory.createSurveyInstance();

        status = survey.addQuestionToSurvey(questionId, surveyId);
        ArrayList<IQuestion> surveyQuestionList = survey.getQuestionsForSurvey(courseId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("surveyQuestionList", surveyQuestionList);
        model.addAttribute("courseName", courseName);
        return "survey/createSurvey";
    }
    
    @GetMapping("/survey/deleteQuestionFromSurvey")
    public String deleteQuestionFromSurvey(@RequestParam("questionId") long questionId, @RequestParam("surveyId") long surveyId, @RequestParam("courseId") String courseId, @RequestParam("courseName") String courseName, Model model) {

        boolean status;
        ISurveyAbstractFactory surveyAbstractFactory = Injector.instance().getSurveyAbstractFactory();
        ISurvey survey = surveyAbstractFactory.createSurveyInstance();

        status = survey.deleteQuestionFromSurvey(questionId, surveyId);
        ArrayList<IQuestion> surveyQuestionList = survey.getQuestionsForSurvey(courseId);
        model.addAttribute("surveyId", surveyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseName", courseName);
        model.addAttribute("surveyQuestionList", surveyQuestionList);
        return "survey/createSurvey";
    }
    
}
