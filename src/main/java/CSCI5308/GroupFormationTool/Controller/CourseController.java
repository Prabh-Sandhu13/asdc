package CSCI5308.GroupFormationTool.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.AccessControl.ICourse;
import CSCI5308.GroupFormationTool.AccessControl.ICourseRepository;
import CSCI5308.GroupFormationTool.AccessControl.ICourseService;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.AccessControl.IUserCoursesService;
import CSCI5308.GroupFormationTool.AccessControl.IUserService;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.Model.User;


@Controller
public class CourseController {

	private ICourseService courseService;
	private IUserService userService;
	private IUserCoursesService userCoursesService;

	@GetMapping("/courseList")
	public String courseList(Model model) {

		courseService = Injector.instance().getCourseService();

		ArrayList<ICourse> courseList = null;
		String userRole = null;
		ArrayList<ICourse> StudentCourseList = null;
		ArrayList<ICourse> TACourseList = null;
		ArrayList<ICourse> InstructorCourseList = null;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		userService = Injector.instance().getUserService();
		userCoursesService = Injector.instance().getUserCoursesService();

		if (!(authentication instanceof AnonymousAuthenticationToken)) {

			String emailId = authentication.getPrincipal().toString();

			if (userService.checkCurrentUserIsAdmin(emailId)) {

				courseList = courseService.getAllCourses();
				model.addAttribute("courses", courseList);
				return "admin/allCourses";

			} else {

				userRole = userCoursesService.getUserRoleByEmailId(emailId);

				if (userRole.equals("Guest")) {

					courseList = courseService.getAllCourses();

					model.addAttribute("courses", courseList);
					return "guest/guestCourses";
				}

				else if (userRole.equals("Student")) {

					StudentCourseList = userCoursesService.getStudentCourses(emailId);
					model.addAttribute("courses", StudentCourseList);
					return "student/studentCourses";
				} else if (userRole.equals("TA")) {
					TACourseList = userCoursesService.getTACourses(emailId);
					StudentCourseList = userCoursesService.getStudentCourses(emailId);
					model.addAttribute("studentCourses", StudentCourseList);
					model.addAttribute("taCourses", TACourseList);
					return "ta/taCourses";
				} else if (userRole.equals("Instructor")) {
					InstructorCourseList = userCoursesService.getInstructorCourses(emailId);
					model.addAttribute("courses", InstructorCourseList);
					return "instructor/instructorCourses";
				}
			}

		}

		return "guest/guestCourses";

	}

	@GetMapping(value = "/courseDetails")
	public String courseDetail(@RequestParam(value = "courseName") String courseName, Model model) {
		model.addAttribute("courseName", courseName);
		return "courseDetails";
	}
	
	@GetMapping(value = "/enrollTA")
	public String enrollTA(@RequestParam(value = "courseId") String courseId, Model model) {
		userCoursesService = Injector.instance().getUserCoursesService();
		ArrayList<IUser> taList = null;
		taList = userCoursesService.getTAForCourse(courseId);
		System.out.println(taList);
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("taList", taList);
		model.addAttribute("courseId", courseId);
		return "ta/enrollTA";
	}
	
	@PostMapping("/enrollTA")
	public String addTA(@RequestParam(value = "courseId") String courseId,
			@ModelAttribute User user, Model model)
	{
		userCoursesService = Injector.instance().getUserCoursesService();
		boolean success = userCoursesService.enrollTAForCourseUsingEmailId(user, courseId);
		System.out.println("_______**********"+success);
		ArrayList<IUser> taList = null;
		if(success) {
			model.addAttribute("success", "added");
		} else {
			model.addAttribute("error", "not added");
		}
		taList = userCoursesService.getTAForCourse(courseId);
		System.out.println(taList);
		model.addAttribute("taList", taList);
		model.addAttribute("courseId", courseId);
//		user.setEmailId(null);
		return "ta/enrollTA";
	}
	
	@GetMapping("/uploadCSVFile")
    public String uploadCSVFile(@RequestParam(value = "courseId") String courseId, Model model) {
		model.addAttribute("CourseId", courseId);
        return "instructor\\uploadCSVFile";
    }
	
	@GetMapping("/admin/allAdminCourses")
	public String allCourses(Model model)
	{
		ICourseRepository courseDB = Injector.instance().getCourseRepository();
		List<ICourse> allCourses = courseDB.getAllCourses();
		model.addAttribute("courses", allCourses);
		return "admin\\allCourses";
	}
	
	
	@GetMapping("/admin/addCourse")
	public String addCourseForm(Model model)
	{
		model.addAttribute("course", new Course());
	    return "admin\\addCourse";
	}
	
	
	@PostMapping("/admin/addCourse")
	public String addCourse(@ModelAttribute Course course, Model model)
	{
		Course c = new Course();
		c.createCourse(course);
		ICourseRepository courseDB = Injector.instance().getCourseRepository();
		List<ICourse> allCourses = courseDB.getAllCourses();
		model.addAttribute("courses", allCourses);
		return "admin\\allCourses";
	}
	
	
	@RequestMapping(value = "/admin/deleteCourse", method = {RequestMethod.GET, RequestMethod.POST}) 
	public String deleteCourse(@RequestParam String id, Model model)
	{
		Course c = new Course();
		c.setId(id);
		c.deleteCourse(id);
		ICourseRepository courseDB = Injector.instance().getCourseRepository();
		List<ICourse> allCourses = courseDB.getAllCourses();
		model.addAttribute("courses", allCourses);
		return "admin\\allCourses";
	}	

	// Code referenced from - "https://attacomsian.com/blog/spring-boot-upload-parse-csv-file#"
		@PostMapping("/uploadCSVFile")
	    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model, @RequestParam(name = "courseId") String courseId) {
			Map<Integer,List<StudentCSV>> studentLists = new HashMap<Integer,List<StudentCSV>>();
	        if (file.isEmpty()) {
	            model.addAttribute("message", "Please select a CSV file to upload.");
	            model.addAttribute("status", false);
	        } else {

	            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

	                CsvToBean<StudentCSV> csvToBean = new CsvToBeanBuilder(reader)
	                        .withType(StudentCSV.class)
	                        .withIgnoreLeadingWhiteSpace(true)
	                        .build();

	                List<StudentCSV> students = (List<StudentCSV>)csvToBean.parse();

	                StudentCSV student = new StudentCSV();
	                System.out.println(courseId);
	                studentLists = student.createStudent(students, courseId);

	                model.addAttribute("newStudentList", studentLists.get(0));
	                model.addAttribute("oldStudentList", studentLists.get(1));
	                model.addAttribute("badData", studentLists.get(2));
	                
	                model.addAttribute("status", true);

	            } catch (Exception ex) {
	                model.addAttribute("message", "An error occurred while processing the CSV file.");
	                model.addAttribute("status", false);
	            }
	        }

	        return "instructor\\CSVSuccessTable";
	    }

}
