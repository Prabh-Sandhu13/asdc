package CSCI5308.GroupFormationTool.Controller;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
import CSCI5308.GroupFormationTool.Model.Course;
import CSCI5308.GroupFormationTool.Model.StudentCSV;
import CSCI5308.GroupFormationTool.Model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {

    private ICourseService courseService;
    private IUserService userService;
    private IUserCoursesService userCoursesService;
    private IStudentService studentService;

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
            } else if (userRole.equals("Student")) {

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
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("taList", taList);
        model.addAttribute("courseId", courseId);
        return "ta/enrollTA";
    }

    @PostMapping("/enrollTA")
    public String addTA(@RequestParam(value = "courseId") String courseId, @ModelAttribute User user, Model model) {

        userCoursesService = Injector.instance().getUserCoursesService();
        boolean success = userCoursesService.enrollTAForCourseUsingEmailId(user, courseId);
        ArrayList<IUser> taList = null;
        if (success) {
            model.addAttribute("success", "added");
        } else {
            model.addAttribute("error", "not added");
        }
        taList = userCoursesService.getTAForCourse(courseId);
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
    public String allCourses(Model model) {
        ICourseRepository courseDB = Injector.instance().getCourseRepository();
        List<ICourse> allCourses = courseDB.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "admin\\allCourses";
    }

    @GetMapping("/admin/addCourse")
    public String addCourseForm(Model model) {
        model.addAttribute("course", new Course());
        return "admin\\addCourse";
    }

    @PostMapping("/admin/addCourse")
    public String addCourse(@ModelAttribute Course course, Model model) {
        ICourseRepository courseRepository = Injector.instance().getCourseRepository();
        boolean status = courseRepository.createCourse(course);
        List<ICourse> allCourses = courseRepository.getAllCourses();

        if (status) {
            model.addAttribute("successMessage", "The course " + course.getName() + " was successfully added!");
        } else {
            model.addAttribute("failureMessage",
                    "The course " + course.getName() + " could not be added since the " + "course already exists!");

        }
        model.addAttribute("courses", allCourses);
        return "admin\\allCourses";
    }

    @RequestMapping(value = "/admin/deleteCourse", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteCourse(@RequestParam String id, Model model) {
        ICourseRepository courseRepository = Injector.instance().getCourseRepository();
        boolean status = courseRepository.deleteCourse(id);
        List<ICourse> allCourses = courseRepository.getAllCourses();

        if (status) {
            model.addAttribute("successMessage", "The course " + id + " was successfully deleted!");
        } else {
            model.addAttribute("failureMessage",
                    "The course could not be deleted since the course is used by a student/TA/instructor.");
        }
        model.addAttribute("courses", allCourses);
        return "admin\\allCourses";
    }

    // Code referenced from -
    // "https://attacomsian.com/blog/spring-boot-upload-parse-csv-file#"
    @PostMapping("/uploadCSVFile")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model,
                                @RequestParam(name = "courseId") String courseId) {

        Map<Integer, List<StudentCSV>> studentLists = new HashMap<Integer, List<StudentCSV>>();

        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a CSV file to upload.");
            model.addAttribute("status", false);
        } else {

            studentService = Injector.instance().getStudentService();

            studentLists = studentService.createStudent(file, courseId);

            if (studentLists != null) {

                model.addAttribute("newStudentList", studentLists.get(DomainConstants.newStudents));
                model.addAttribute("oldStudentList", studentLists.get(DomainConstants.oldStudents));
                model.addAttribute("badData", studentLists.get(DomainConstants.badData));
                model.addAttribute("course", courseId);
                model.addAttribute("status", true);

            } else {
                model.addAttribute("course", courseId);
                model.addAttribute("message", "An error occurred while processing the CSV file.");
                model.addAttribute("status", false);
            }
        }

        return "instructor\\CSVSuccessTable";
    }

}
