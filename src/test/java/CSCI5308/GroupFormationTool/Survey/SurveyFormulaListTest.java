package CSCI5308.GroupFormationTool.Survey;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SurveyFormulaListTest {
    
    private ITestSurveyAbstractFactory surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
    private ISurveyFormulaRepository surveyFormulaRepository;
    
    @BeforeEach
    void init() {
        surveyFormulaRepository = surveyAbstractFactory.createSurveyFormulaRepositoryMock();
        SurveyInjector.instance().setSurveyFormulaRepository(surveyFormulaRepository);
    }
    @Test
    public void getSurveyRulesTest() {
        SurveyFormulaList surveyFormulaList= surveyAbstractFactory.createSurveyFormulaListObj();
        ArrayList<SurveyFormula> rules= surveyAbstractFactory.createSurveyFormulaListInstance();
        surveyFormulaList.setSurveyRules(rules);
        assertNotNull(surveyFormulaList.getSurveyRules());
    }
    
    @Test
    public void setSurveyRulesTest() {
        SurveyFormulaList surveyFormulaList= surveyAbstractFactory.createSurveyFormulaListObj();
        ArrayList<SurveyFormula> rules= surveyAbstractFactory.createSurveyFormulaListInstance();
        assertNull(surveyFormulaList.getSurveyRules());
        surveyFormulaList.setSurveyRules(rules);
        assertNotNull(surveyFormulaList.getSurveyRules());
    }

}
