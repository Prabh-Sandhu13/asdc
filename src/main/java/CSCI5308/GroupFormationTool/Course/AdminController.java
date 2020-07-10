package CSCI5308.GroupFormationTool.Course;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.User.IUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {

    @GetMapping("/admin/allCourses")
    public String adminCourses(Model model) {
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        ICourse course = courseAbstractFactory.createCourseInstance();
        List<ICourse> allCourses = course.getAllCourses();
        model.addAttribute("courses", allCourses);
        return "course/allCourses";
    }

    @GetMapping("/admin/assignInstructor")
    public String assignInstructor(Model model, @RequestParam(name = "courseId") String courseId) {
        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        IUserCourses userCourses = courseAbstractFactory.createUserCoursesInstance();
        ICourse course = courseAbstractFactory.createCourseInstance();

        ICourse courseById = course.getCourseById(courseId);

        ArrayList<IUser> allUsersCurrentlyNotInstructors = userCourses
                .usersCurrentlyNotInstructorsForCourse(courseId);

        ArrayList<IUser> instructorList = userCourses.getInstructorsForCourse(courseId);

        model.addAttribute("instructorList", instructorList);
        model.addAttribute("course", courseById);
        model.addAttribute("users", allUsersCurrentlyNotInstructors);
        return "course/assignInstructor";
    }

    @PostMapping("/admin/assignInstructor")
    public String assignInstructorToCourse(@RequestParam(name = "instructor") Long instructor,
                                           @RequestParam(name = "id") String courseId, Model model) {

        ICourseAbstractFactory courseAbstractFactory = Injector.instance().getCourseAbstractFactory();
        IUserCourses userCourses = courseAbstractFactory.createUserCoursesInstance();
        ICourse course = courseAbstractFactory.createCourseInstance();
        ICourse courseById = course.getCourseById(courseId);
        boolean success = userCourses.addInstructorsToCourse(instructor, courseId);
        ArrayList<IUser> instructorList = userCourses.getInstructorsForCourse(courseId);
        model.addAttribute("instructorList", instructorList);
        model.addAttribute("course", courseById);
        if (success) {
            model.addAttribute("success", DomainConstants.instructorAddSuccess);
        } else {
            model.addAttribute("failure", DomainConstants.instructorAddFailure);
        }
        return "redirect:/course/assignInstructor?courseId=" + courseId;

    }
}
