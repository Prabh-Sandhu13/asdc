package CSCI5308.GroupFormationTool.Survey;

public interface ISurveyRepository {

    boolean checkIfSurveyCreated(String courseId);

    boolean checkIfSurveyPublished(String courseId);

    boolean checkIfSurveyHasFormula(String courseId);

    boolean publishSurvey(String courseId);

    int getSurveyIdByCourseId(String courseId);
}
