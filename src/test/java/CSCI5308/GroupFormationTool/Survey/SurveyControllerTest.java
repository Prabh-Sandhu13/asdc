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

@WebMvcTest(SurveyController.class)
public class SurveyControllerTest {

    @Autowired
    private MockMvc mockMvc;
    
    private ITestSurveyAbstractFactory surveyAbstractFactory;
    private ISurveyRepository surveyRepository;

    @Test
    void publishSurveyTest() throws Exception {
        String courseId = "1";
        surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
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
    void createSurvey() throws Exception{
    	String courseId = "1";
        surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        surveyRepository = surveyAbstractFactory.createSurveyRepositoryMock();
        SurveyInjector.instance().setSurveyRepository(surveyRepository);
        when(surveyRepository.createSurvey(courseId)).thenReturn(1);
        when(surveyRepository.getQuestionsForSurvey(courseId)).thenReturn(null);
        this.mockMvc.perform(get("/survey/createSurvey")
        		.param("courseName", "Cloud")
        		.param("surveyId", "1")
        		.param("courseId", courseId)
        		.param("surveyQuestionList","")
        		.param("questionList", ""))
        		.andExpect(status().isOk())
        		.andExpect(view().name("survey/createSurvey"))
        		.andDo(MockMvcResultHandlers.print())
        		.andReturn();        
    }
    
    @Test
    void addQuestionToSurvey() throws Exception{
    	long questionId = 1;
    	long surveyId = 1;
    	String courseId = "1";
        surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        surveyRepository = surveyAbstractFactory.createSurveyRepositoryMock();
        SurveyInjector.instance().setSurveyRepository(surveyRepository);
        when(surveyRepository.getQuestionsForSurvey(courseId)).thenReturn(null);
        when(surveyRepository.addQuestionToSurvey(questionId, surveyId)).thenReturn(true);
        this.mockMvc.perform(get("/survey/createSurvey")
        		.param("courseName", "Cloud")
        		.param("surveyId", surveyId+"")
        		.param("courseId", courseId)
        		.param("surveyQuestionList",""))
				.andExpect(status().isOk())
				.andExpect(view().name("survey/createSurvey"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();        
        when(surveyRepository.addQuestionToSurvey(questionId, surveyId)).thenReturn(false);
        this.mockMvc.perform(get("/survey/createSurvey")
        		.param("courseName", "Cloud")
        		.param("surveyId", surveyId+"")
        		.param("courseId", courseId)
        		.param("surveyQuestionList",""))
				.andExpect(status().isOk())
				.andExpect(view().name("survey/createSurvey"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();        
    }
    
    @Test
    void deleteQuestionFromSurvey() throws Exception{
    	long questionId = 1;
    	long surveyId = 1;
    	String courseId = "1";
        surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        surveyRepository = surveyAbstractFactory.createSurveyRepositoryMock();
        SurveyInjector.instance().setSurveyRepository(surveyRepository);
        when(surveyRepository.getQuestionsForSurvey(courseId)).thenReturn(null);
        when(surveyRepository.deleteQuestionFromSurvey(questionId, surveyId)).thenReturn(true);
        this.mockMvc.perform(get("/survey/createSurvey")
        		.param("courseName", "Cloud")
        		.param("surveyId", surveyId+"")
        		.param("courseId", courseId)
        		.param("surveyQuestionList",""))
				.andExpect(status().isOk())
				.andExpect(view().name("survey/createSurvey"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();        
        when(surveyRepository.deleteQuestionFromSurvey(questionId, surveyId)).thenReturn(false);
        this.mockMvc.perform(get("/survey/createSurvey")
        		.param("courseName", "Cloud")
        		.param("surveyId", surveyId+"")
        		.param("courseId", courseId)
        		.param("surveyQuestionList",""))
				.andExpect(status().isOk())
				.andExpect(view().name("survey/createSurvey"))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();        
    }
    /*
    @Test
    void searchQuestionForSurvey() throws Exception{
        surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        surveyRepository = surveyAbstractFactory.createSurveyRepositoryMock();
        SurveyInjector.instance().setSurveyRepository(surveyRepository);
        when(surveyRepository.getSurveyQuestions("1")).thenReturn(null);
        when(surveyRepository.getSurveyQuestionListForTA(null, 1, "")).thenReturn(null);
        when(surveyRepository.getSurveyQuestionListForInstructor("haard.shah@dal.ca", 1, "")).thenReturn(null);
        this.mockMvc.perform(post("/questionManager/searchQuestionForSurvey")
        		.param("courseName", "Cloud")
        		.param("surveyId", "1")
        		.param("courseId", "1")
        		.param("surveyQuestionList","")
        		.param("questionList", "")
        		.with(csrf()))
		        .andExpect(status().isOk())
		        .andExpect(view().name("survey/createSurvey"))
		        .andDo(MockMvcResultHandlers.print())
		        .andReturn();
    } */

}
