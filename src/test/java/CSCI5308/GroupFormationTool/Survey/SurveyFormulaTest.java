package CSCI5308.GroupFormationTool.Survey;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SurveyFormulaTest {
	private ITestSurveyAbstractFactory surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
    private ISurveyFormulaRepository surveyFormulaRepository;
    private ISurveyFormula surveyFormula;

    @BeforeEach
    void init() {
    	surveyFormulaRepository = surveyAbstractFactory.createSurveyFormulaRepositoryMock();
        SurveyInjector.instance().setSurveyFormulaRepository(surveyFormulaRepository);
        surveyFormula = surveyAbstractFactory.createSurveyFormulaInstance();
    }
    @Test
    public void getCourseIdTest() {
    	surveyFormula.setCourseId("2");
    	assertEquals(surveyFormula.getCourseId(),2);
    }

    @Test
    public void setCourseIdTest() {
    }

    @Test
    public void getSurveyIdTest() {
    }

    @Test
    public void setSurveyIdTest() {
    }

    @Test
    public void getQuestionIdTest() {
    }

    @Test
    public void setQuestionIdTest() {
    }

    @Test
    public void getQuestionTextTest() {
    }

    @Test
    public void setQuestionTextTest() {
    }

    @Test
    public void getQuestionTypeTest() {
    }

    @Test
    public void setQuestionTypeTest() {
    }

    @Test
    public void isCompareSimilarityTest() {
    }

    @Test
    public void setCompareSimilarityTest() {
    }

    @Test
    public void getNumericGreaterThanTest() {
    }

    @Test
    public void setNumericGreaterThanTest() {
    }

    @Test
    public void getNumericLessThanTest() {
    }

    @Test
    public void setNumericLessThanTest() {
    }

    @Test
    public void isFreeTextSimilarityTest() {
    }

    @Test
    public void setFreeTextSimilarityTest() {
    }

    @Test
    public void isCompareDisimilarityTest() {
    }

    @Test
    public void setCompareDisimilarityTest() {
    }

    @Test
    public void isFreeTextDisimilarityTest() {
    }

    @Test
    public void setFreeTextDisimilarityTest() {
    }

    @Test
    public void getSurveyDetailsToSetAlgoTest() {
    }

    @Test
    public void createSurveyFormulaTest() {

    }


}
