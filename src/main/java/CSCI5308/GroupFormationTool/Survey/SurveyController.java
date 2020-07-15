package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    @GetMapping(value = "/survey/createSurveyFormula")
    public String createSurveyFormula(@RequestParam(value = "courseName") String courseName,
                               @RequestParam(value = "courseId") String courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseName", courseName);
        ISurveyFormula surveyFormula = new SurveyFormula();
        ArrayList<SurveyFormula> surveyFormulaList = new  ArrayList<SurveyFormula>();
        surveyFormulaList = surveyFormula.getSurveyDetailsToSetAlgo(courseId);
        System.out.println(surveyFormulaList);
        int surveyId = 0;
        for (SurveyFormula s:surveyFormulaList) {
        	surveyId = s.getSurveyId();
        	break;
        }
        model.addAttribute("surveyId", surveyId);
        SurveyFormulaList formulaList = new SurveyFormulaList();
        formulaList.setSurveyRules(surveyFormulaList);
        model.addAttribute("surveyFormulaList", formulaList);
        return "survey/createSurveyFormula";
    }

    @PostMapping(value = "/survey/saveSurvey")
    public String saveSurvey(@RequestParam(value = "courseName") String courseName,
                             @RequestParam(value = "courseId") String courseId, Model model) {
        return "redirect:/instructorCourseDetails?courseId=" + courseId + "courseName=" + courseName;
    }
    @PostMapping(value = "/survey/saveSurveyFormula")
    public String saveSurveyFormula(@RequestParam(value = "courseName") String courseName,
                             @RequestParam(value = "courseId") String courseId,
                             @RequestParam(value = "surveyId") int surveyId,
    		                 @RequestParam int groupSize,
                             Model model,
                             @ModelAttribute("surveyFormulaList") SurveyFormulaList surveyFormulaList) {
    	System.out.println(surveyFormulaList);
    	ISurveyFormula surveyFormula = new SurveyFormula();
    	surveyFormula.createSurveyFormula(courseId, surveyId, groupSize, surveyFormulaList);
        return "redirect:/instructorCourseDetails?courseId=" + courseId + "&courseName=" + courseName;
    }

    @PostMapping(value = "/survey/publishSurvey")
    public String publishSurvey(@RequestParam(value = "courseName") String courseName,
                                @RequestParam(value = "courseId") String courseId, Model model) {
        return "redirect:/instructorCourseDetails?courseId=" + courseId + "courseName=" + courseName;
    }
}
