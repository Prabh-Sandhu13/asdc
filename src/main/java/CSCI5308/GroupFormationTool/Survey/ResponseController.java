package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Course.CourseInjector;
import CSCI5308.GroupFormationTool.Course.ICourseAbstractFactory;
import CSCI5308.GroupFormationTool.Course.IUserCourses;
import CSCI5308.GroupFormationTool.Question.IQuestion;

@Controller
public class ResponseController {
	private ISurvey surveyInstance;
	private IResponse responseInstance;
	private static final Logger log = LoggerFactory.getLogger(ResponseController.class.getName());
	
    @GetMapping("/courseSurveyResponse")
    public String takeSurvey(@RequestParam(value = "courseName") String courseName,
    		@RequestParam(value = "courseId") String courseId,Model model) {
        ICourseAbstractFactory courseAbstractFactory = CourseInjector.instance().getCourseAbstractFactory();
		ISurveyAbstractFactory surveyAbstractFactory = SurveyInjector.instance().getSurveyAbstractFactory();
		surveyInstance = surveyAbstractFactory.createSurveyInstance();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        IUserCourses userCourses = courseAbstractFactory.createUserCoursesInstance();
        String userRole = null;
        String emailId = authentication.getPrincipal().toString();
        userRole = userCourses.getUserRoleByEmailId(emailId);
            if (userRole.equals(DomainConstants.studentRole)) {
            	log.info("User role is Student");
            	String surveyId = surveyInstance.getSurveyId(courseId);
            	log.info("Survey Id is " + surveyId);
            	log.info("Call to get the survey question for the survey " + surveyId);
            	ArrayList<IQuestion> surveyQuestions = surveyInstance.getSurveyQuestions(surveyId);
            	model.addAttribute("surveyQuestions", surveyQuestions);
            	model.addAttribute("courseName", courseName);
            	model.addAttribute("courseId", courseId);
            	return "course/courseSurveyResponse";
            }
            else {
            	log.warn("Unauthorized user tried to access the response page");
            	return "redirect:login";
            }
    }
    
    @PostMapping("/courseSurveyResponse")
    public String submitSurvey(
    		@RequestParam Map<String,String> searchParams, Model model){
		ISurveyAbstractFactory surveyAbstractFactory = SurveyInjector.instance().getSurveyAbstractFactory();
		responseInstance = surveyAbstractFactory.createResponseInstance();
		log.info("Creating a response list");
		ArrayList<IResponse> responseList= responseInstance.createResponseList(searchParams);
		responseInstance.storeResponses(responseList);
		model.addAttribute("Success",DomainConstants.surveySuccess);
	    return "course/courseSurveySubmitted";				
    }

}
