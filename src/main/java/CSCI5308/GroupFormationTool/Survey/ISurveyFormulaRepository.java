package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

public interface ISurveyFormulaRepository {

    ArrayList<SurveyFormula> getSurveyDetailsToSetAlgo(String courseId);

    String getAlgoIdBySurveyId(int surveyId);

    void updateSurveyGroupSize(int groupSize, int surveyId, String courseId);

    Boolean createAlgo(SurveyFormulaList surveyFormulaList, String generatedAlgoId, int surveyId);

    Boolean deleteExistingAlgo(String algoId);

    Boolean updateAlgoId(String generatedAlgoId, int surveyId);

}
