package CSCI5308.GroupFormationTool.Survey;

public class SurveyDBMock implements ISurveyRepository {
    @Override
    public boolean checkIfSurveyCreated(String courseId) {
        return true;
    }

    @Override
    public boolean checkIfSurveyPublished(String courseId) {
        return true;
    }

    @Override
    public boolean checkIfSurveyHasFormula(String courseId) {
        return true;
    }

    @Override
    public boolean publishSurvey(String courseId) {
        return true;
    }

    @Override
    public int getSurveyIdByCourseId(String courseId) {
        return 1;
    }
}
