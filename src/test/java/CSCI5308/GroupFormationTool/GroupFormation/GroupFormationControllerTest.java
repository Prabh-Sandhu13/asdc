package CSCI5308.GroupFormationTool.GroupFormation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(GroupFormationController.class)
public class GroupFormationControllerTest {

    private ITestGroupFormationAbstractFactory groupFormationAbstractFactory = TestGroupFormationInjector.instance().
            getGroupFormationAbstractFactory();
    private GroupFormationManager groupFormationManager;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void formGroups() throws Exception {
        this.mockMvc.perform(get("/groupFormation/createGroup")
                .param("courseName", "SDC")
                .param("courseId", "CSCI3901"))
                .andExpect(status().isOk())
                .andExpect(view().name("group/createGroup"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    @Test
    void saveGroups() throws Exception {
        groupFormationManager = groupFormationAbstractFactory.createGroupFormationMock();
        GroupFormationInjector.instance().setGroupFormationManager(groupFormationManager);
        String courseId = "CSCI 5308";
        String courseName = "SDC";
        when(groupFormationManager.getGroupsForCourse(courseId)).thenReturn(groupFormationAbstractFactory.
                createGroupsInstance());
        this.mockMvc.perform(post("/groupFormation/saveGroup")
                .param("courseId", courseId)
                .param("courseName", courseName)
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("group/groupDetails"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
