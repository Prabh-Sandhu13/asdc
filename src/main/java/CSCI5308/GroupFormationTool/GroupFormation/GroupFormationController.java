package CSCI5308.GroupFormationTool.GroupFormation;

import CSCI5308.GroupFormationTool.User.IUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.TreeMap;

@Controller
public class GroupFormationController {

    private static final Logger log = LoggerFactory.getLogger(GroupFormationController.class.getName());
    private IGroupFormationManager groupFormationManager;

    @GetMapping(value = "/groupFormation/createGroup")
    public String formGroups(@RequestParam(value = "courseName") String courseName,
                             @RequestParam(value = "courseId") String courseId, Model model) {
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseName", courseName);
        return "group/createGroup";
    }

    @PostMapping(value = "/groupFormation/saveGroup")
    public String saveGroups(@RequestParam(value = "courseName") String courseName,
                             @RequestParam(value = "courseId") String courseId, Model model) {
        log.info("Fetching the groups for the course from the Group Formation Manager");
        groupFormationManager = GroupFormationInjector.instance().getGroupFormationManager();
        TreeMap<Integer, ArrayList<IUser>> groups = groupFormationManager.getGroupsForCourse(courseId);
        model.addAttribute("groups", groups);
        model.addAttribute("courseId", courseId);
        model.addAttribute("courseName", courseName);
        return "group/groupDetails";
    }
}
