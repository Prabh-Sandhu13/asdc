package CSCI5308.GroupFormationTool.Course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(InstructorCourseController.class)
public class InstructorCourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void courseDetailsTest() throws Exception {
        this.mockMvc.perform(get("/instructorCourseDetails")
                .param("courseName", "SDC")
                .param("courseId", "CSCI3901"))
                .andExpect(status().isOk())
                .andExpect(view().name("course/instructorCourseDetails"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
