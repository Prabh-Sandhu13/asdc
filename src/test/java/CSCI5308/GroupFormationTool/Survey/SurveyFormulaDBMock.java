package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

public class SurveyFormulaDBMock implements ISurveyFormulaRepository{

    @Override
    public ArrayList<SurveyFormula> getSurveyDetailsToSetAlgo(String courseId) {
        ITestSurveyAbstractFactory surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        ArrayList<SurveyFormula> rules= surveyAbstractFactory.createSurveyFormulaListInstance();
        return rules;
    }

    @Override
    public String getAlgoIdBySurveyId(int surveyId) {
        String algoId = "newAlgo";
        return algoId;
    }

    @Override
    public void updateSurveyGroupSize(int groupSize, int surveyId, String courseId) {
        ITestSurveyAbstractFactory surveyAbstractFactory = TestSurveyInjector.instance().getSurveyAbstractFactory();
        ISurveyFormulaRepository surveyFormulaRepository = surveyAbstractFactory.createSurveyFormulaRepositoryMock();
        SurveyInjector.instance().setSurveyFormulaRepository(surveyFormulaRepository);
        surveyFormulaRepository.updateSurveyGroupSize(groupSize, surveyId, courseId);
    }

    @Override
    public Boolean createAlgo(SurveyFormulaList surveyFormulaList, String generatedAlgoId, int surveyId) {
        return true;
    }

    @Override
    public Boolean deleteExistingAlgo(String algoId) {
        return true;
    }

    @Override
    public Boolean updateAlgoId(String generatedAlgoId, int surveyId) {
        return true;
    }

}
