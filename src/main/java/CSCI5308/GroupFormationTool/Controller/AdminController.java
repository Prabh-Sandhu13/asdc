package CSCI5308.GroupFormationTool.Controller;

import CSCI5308.GroupFormationTool.AccessControl.*;
import CSCI5308.GroupFormationTool.DomainConstants;
import CSCI5308.GroupFormationTool.Injector;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    IUserCoursesService userCoursesService;
    ICourseService courseService;

    @GetMapping("/admin/allCourses")
    public String adminCourses(Model model) {

        ICourseRepository courseDB = Injector.instance().getCourseRepository();
        List<ICourse> allCourses = courseDB.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "admin/allCourses";
    }

    @GetMapping("/admin/assignInstructor")
    public String assignInstructor(Model model, @RequestParam(name = "courseId") String courseId) {

        userCoursesService = Injector.instance().getUserCoursesService();
        courseService = Injector.instance().getCourseService();

        ICourse course = courseService.getCourseById(courseId);

        ArrayList<IUser> allUsersCurrentlyNotInstructors = userCoursesService
                .usersCurrentlyNotInstructorsForCourse(courseId);

        ArrayList<IUser> instructorList = userCoursesService.getInstructorsForCourse(courseId);

        model.addAttribute("instructorList", instructorList);
        model.addAttribute("course", course);
        model.addAttribute("users", allUsersCurrentlyNotInstructors);
        return "admin/assignInstructor";
    }

    @PostMapping("/admin/assignInstructor")
    public String assignInstructorToCourse(@RequestParam(name = "instructor") Long instructor,
                                           @RequestParam(name = "id") String courseId, Model model) {

        userCoursesService = Injector.instance().getUserCoursesService();
        courseService = Injector.instance().getCourseService();

        ICourse course = courseService.getCourseById(courseId);

        boolean success = userCoursesService.addInstructorsToCourse(instructor, courseId);

        ArrayList<IUser> instructorList = userCoursesService.getInstructorsForCourse(courseId);

        model.addAttribute("instructorList", instructorList);
        model.addAttribute("course", course);

        if (success) {
            model.addAttribute("success", DomainConstants.instructorAddSuccess);
        } else {
            model.addAttribute("failure", DomainConstants.instructorAddFailure);
        }
        return "redirect:/admin/assignInstructor?courseId=" + courseId;

    }
}
