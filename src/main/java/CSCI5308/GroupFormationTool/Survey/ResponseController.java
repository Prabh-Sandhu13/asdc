package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
            /*	for(IQuestion q: surveyQuestions) {
            		System.out.println("======================================");
            		System.out.println(q.getTitle());
            		System.out.println(q.getText());
            		if(q.getType() == 2 || q.getType() == 3) {
            			for (IChoice choice:q.getChoices() ) {
            				System.out.println("Text : "+choice.getText());
            				System.out.println("Value : "+choice.getValue());
            			}
            		}
            	} */
            	model.addAttribute("surveyQuestions", surveyQuestions);
            	model.addAttribute("courseName", courseName);
            	return "course/courseSurveyResponse";
            }
            else {
            	return "redirect:login";
            }
    }
}
