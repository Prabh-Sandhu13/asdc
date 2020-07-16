package CSCI5308.GroupFormationTool.Survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void publishSurveyTest() throws Exception {
        String courseId = "1";
        ITestSurveyAbstractFactory surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        ISurveyRepository surveyRepository;
        surveyRepository = surveyAbstractFactory.createSurveyRepositoryMock();
        SurveyInjector.instance().setSurveyRepository(surveyRepository);
        when(surveyRepository.getSurveyIdByCourseId(courseId)).thenReturn(1);
        when(surveyRepository.publishSurvey(anyString())).thenReturn(true);
        this.mockMvc.perform(post("/survey/publishSurvey")
                .param("courseName", "SDC")
                .param("courseId", "CSCI3901")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("course/instructorCourseDetails"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

        when(surveyRepository.publishSurvey(anyString())).thenReturn(false);
        this.mockMvc.perform(post("/survey/publishSurvey")
                .param("courseName", "SDC")
                .param("courseId", "CSCI3901")
                .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("course/instructorCourseDetails"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
    
    @Test
    void createSurveyFormula() throws Exception {
        String courseId = "1";
        ITestSurveyAbstractFactory surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        ISurveyFormulaRepository surveyFormulaRepository;
        surveyFormulaRepository = surveyAbstractFactory.createSurveyFormulaRepositoryMock();
        ArrayList<SurveyFormula> rules= surveyAbstractFactory.createSurveyFormulaListInstance();
        SurveyFormulaList rulesList= surveyAbstractFactory.createSurveyFormulaListObj();
        SurveyInjector.instance().setSurveyFormulaRepository(surveyFormulaRepository);
        when(surveyFormulaRepository.getSurveyDetailsToSetAlgo(courseId)).thenReturn(rules);
        when(surveyFormulaRepository.createAlgo(rulesList, "newAlgo", 1)).thenReturn(true);
        this.mockMvc.perform(get("/survey/createSurveyFormula")
                .param("courseName", "advSDC")
                .param("courseId", "1"))
                .andExpect(status().isOk())
                .andExpect(view().name("survey/createSurveyFormula"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
