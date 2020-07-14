package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Course.ICourseAbstractFactory;
import CSCI5308.GroupFormationTool.Course.IUserCourses;
import CSCI5308.GroupFormationTool.Password.IPasswordAbstractFactory;
import CSCI5308.GroupFormationTool.Password.IPolicy;
import CSCI5308.GroupFormationTool.Question.IChoice;
import CSCI5308.GroupFormationTool.Question.IQuestion;

@Controller
public class ResponseController {
	private ISurvey surveyInstance;
    @GetMapping("/courseSurveyResponse")
    public String takeSurvey(@RequestParam(value = "courseName") String courseName,
    		@RequestParam(value = "courseId") String courseId,Model model) {
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
		ISurveyAbstractFactory passwordAbstractFactory = Injector.instance().getSurveyAbstractFactory();
		surveyInstance = passwordAbstractFactory.createSurveyInstance();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        IUserCourses userCourses = courseAbstractFactory.createUserCoursesInstance();
        String userRole = null;
        String emailId = authentication.getPrincipal().toString();
        userRole = userCourses.getUserRoleByEmailId(emailId);
            if (userRole.equals(DomainConstants.studentRole)) {
            	String surveyId = surveyInstance.getSurveyId(courseId);
            	ArrayList<IQuestion> surveyQuestions = surveyInstance.getSurveyQuestions(surveyId);
            	model.addAttribute("surveyQuestions", surveyQuestions);
            	model.addAttribute("courseName", courseName);
            	model.addAttribute("courseId", courseId);
            	return "course/courseSurveyResponse";
            }
            else {
            	return "redirect:login";
            }
    }
    
    @PostMapping("/courseSurveyResponse")
    public String submitSurvey(
    		@RequestParam Map<String,String> searchParams, Model model){
    	for (Map.Entry<String,String> entry : searchParams.entrySet()) {  
            System.out.println("Key = " + entry.getKey() + 
                             ", Value = " + entry.getValue()); }
    	
				return "redirect:courseList";
    	
    }
}
