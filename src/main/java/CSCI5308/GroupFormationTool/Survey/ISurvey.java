package CSCI5308.GroupFormationTool.Survey;

public interface ISurvey {

    boolean checkIfSurveyCreated(String courseId);

    boolean checkIfSurveyPublished(String courseId);

    boolean checkIfSurveyHasFormula(String courseId);

    int checkIfGroupsCanBeFormedForSurvey(String courseId);

    boolean publishSurvey(String courseId);

    int getSurveyIdByCourseId(String courseId);
}
