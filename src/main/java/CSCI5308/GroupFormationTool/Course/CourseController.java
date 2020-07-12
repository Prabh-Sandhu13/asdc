package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {

    private IUser userInstance;
    private IStudentCSV studentCSV;
    private static final Logger Log = LoggerFactory.getLogger(CourseController.class.getName());

    @GetMapping("/courseList")
    public String courseList(Model model) {
        Log.info("Inside course controller");
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        ArrayList<ICourse> courseList = null;
        String userRole = null;
        ArrayList<ICourse> studentCourseList = null;
        ArrayList<ICourse> taCourseList = null;
        ArrayList<ICourse> instructorCourseList = null;
        IUserCourses userCourses = courseAbstractFactory.createUserCoursesInstance();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userInstance = userAbstractFactory.createUserInstance();
        String emailId = authentication.getPrincipal().toString();
        ICourse course = courseAbstractFactory.createCourseInstance();

        if (userInstance.checkCurrentUserIsAdmin(emailId)) {
            courseList = course.getAllCourses();
            model.addAttribute("courses", courseList);
            return "course/allCourses";
        } else {
            userRole = userCourses.getUserRoleByEmailId(emailId);
            if (userRole.equals(DomainConstants.guestRole)) {
                courseList = course.getAllCourses();
                model.addAttribute("courses", courseList);
                return "course/guestCourses";
            } else if (userRole.equals(DomainConstants.studentRole)) {
                studentCourseList = userCourses.getStudentCourses(emailId);
                model.addAttribute("courses", studentCourseList);
                return "course/studentCourses";
            } else if (userRole.equals(DomainConstants.tARole)) {
                taCourseList = userCourses.getTACourses(emailId);
                studentCourseList = userCourses.getStudentCourses(emailId);
                model.addAttribute("studentCourses", studentCourseList);
                model.addAttribute("taCourses", taCourseList);
                return "course/taCourses";
            } else if (userRole.equals(DomainConstants.instructorRole)) {
                instructorCourseList = userCourses.getInstructorCourses(emailId);
                model.addAttribute("courses", instructorCourseList);
                return "course/instructorCourses";
            }
        }
        return "course/guestCourses";
    }

    @GetMapping(value = "/courseDetails")
    public String courseDetail(@RequestParam(value = "courseName") String courseName, Model model) {
        model.addAttribute("courseName", courseName);
        return "course/courseDetails";
    }

    @GetMapping(value = "/enrollTA")
    public String enrollTA(@RequestParam(value = "courseId") String courseId, Model model) {
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        ArrayList<IUser> taList = null;
        IUserCourses userCourses = courseAbstractFactory.createUserCoursesInstance();
        taList = userCourses.getTAForCourse(courseId);
        IUser user = userAbstractFactory.createUserInstance();
        model.addAttribute("user", user);
        model.addAttribute("taList", taList);
        model.addAttribute("courseId", courseId);
        return "course/enrollTA";
    }

    @PostMapping("/enrollTA")
    public String addTA(@RequestParam(value = "courseId") String courseId, @ModelAttribute("user") User user, Model model) {
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        IUserCourses userCourses = courseAbstractFactory.createUserCoursesInstance();
        boolean success = userCourses.enrollTAForCourseUsingEmailId(user, courseId);
        ArrayList<IUser> taList = null;
        if (success) {
            model.addAttribute("success", DomainConstants.taAddSuccess);
        } else {
            model.addAttribute("error", DomainConstants.taAddFailure);
        }
        taList = userCourses.getTAForCourse(courseId);
        model.addAttribute("taList", taList);
        model.addAttribute("courseId", courseId);
        return "course/enrollTA";
    }

    @GetMapping("/uploadCSVFile")
    public String uploadCSVFile(@RequestParam(value = "courseId") String courseId, Model model) {
        model.addAttribute("CourseId", courseId);
        return "course/uploadCSVFile";
    }

    @GetMapping("/admin/allAdminCourses")
    public String allCourses(Model model) {
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        ICourse course = courseAbstractFactory.createCourseInstance();
        List<ICourse> allCourses = course.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "course/allCourses";
    }

    @GetMapping("/admin/addCourse")
    public String addCourseForm(Model model) {
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        model.addAttribute("course", courseAbstractFactory.createCourseInstance());
        return "course/addCourse";
    }

    @PostMapping("/admin/addCourse")
    public String addCourse(@ModelAttribute("course") Course course, Model model) {
        boolean status = course.createCourse();
        List<ICourse> allCourses = course.getAllCourses();
        if (status) {
            model.addAttribute("successMessage", DomainConstants.addCourseSuccess);
        } else {
            model.addAttribute("failureMessage", DomainConstants.addCourseFailure);
        }
        model.addAttribute("courses", allCourses);
        return "course/allCourses";
    }

    @RequestMapping(value = "/admin/deleteCourse", method = {RequestMethod.GET, RequestMethod.POST})
    public String deleteCourse(@RequestParam(value = "id") String id, Model model) {
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        ICourse course = courseAbstractFactory.createCourseInstance();
        boolean status = course.deleteCourse(id);
        List<ICourse> allCourses = course.getAllCourses();
        if (status) {
            model.addAttribute("successMessage", DomainConstants.deleteCourseSuccess);
        } else {
            model.addAttribute("failureMessage", DomainConstants.deleteCourseFailure);
        }
        model.addAttribute("courses", allCourses);
        return "course/allCourses";
    }

    // Code referred from "https://attacomsian.com/blog/spring-boot-upload-parse-csv-file#"
    @PostMapping("/uploadCSVFile")
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model,
                                @RequestParam(name = "courseId") String courseId) {

        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        Map<Integer, List<StudentCSV>> studentLists = courseAbstractFactory.createStudentHashMapInstance();
        if (file.isEmpty()) {
            model.addAttribute("message", DomainConstants.invalidFile);
            model.addAttribute("status", false);
        } else {
            studentCSV = courseAbstractFactory.createStudentCSVInstance();
            studentLists = studentCSV.createStudent(file, courseId);
            if (studentLists != null) {
                model.addAttribute("newStudentList", studentLists.get(DomainConstants.newStudents));
                model.addAttribute("oldStudentList", studentLists.get(DomainConstants.oldStudents));
                model.addAttribute("badData", studentLists.get(DomainConstants.badData));
                model.addAttribute("course", courseId);
                model.addAttribute("status", true);
            } else {
                model.addAttribute("course", courseId);
                model.addAttribute("message", DomainConstants.csvFileProcessingError);
                model.addAttribute("status", false);
            }
        }
        return "course/CSVSuccessTable";
    }

}
