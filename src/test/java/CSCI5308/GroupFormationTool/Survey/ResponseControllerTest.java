package CSCI5308.GroupFormationTool.Survey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SurveyController.class)
public class ResponseControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
    
    private ITestSurveyAbstractFactory surveyAbstractFactory;
    private IResponseRepository responseRepository;
    
    @Test
    void takeSurveyTest() throws Exception {
        surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        responseRepository = surveyAbstractFactory.createResponseRepositoryMock();
        SurveyInjector.instance().setResponseRepository(responseRepository);
    }
    
    @Test
    void submitSurveyTest() throws Exception {
        surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        responseRepository = surveyAbstractFactory.createResponseRepositoryMock();
        SurveyInjector.instance().setResponseRepository(responseRepository);
    }

}
