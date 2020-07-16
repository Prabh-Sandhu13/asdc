package CSCI5308.GroupFormationTool.Survey;

import static org.mockito.Mockito.mock;

public class TestSurveyAbstractFactory implements ITestSurveyAbstractFactory {
    @Override
    public ISurvey createSurveyInstance() {
        return new Survey();
    }
    
    @Override
    public ISurveyFormula createSurveyFormulaInstance() {
        return new SurveyFormula();
    }

    @Override
    public SurveyRepository createSurveyRepositoryMock() {
        return mock(SurveyRepository.class);
    }
    
    @Override
    public SurveyFormulaRepository createSurveyFormulaRepositoryMock() {
        return mock(SurveyFormulaRepository.class);
    }

    @Override
    public ResponseRepository createResponseRepositoryMock() {
        return mock(ResponseRepository.class);
    }

    @Override
    public IResponse createResponseInstance() {
        return new Response();
    }
}
