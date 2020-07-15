package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurveyRepository {


    boolean checkIfSurveyCreated(String courseId);

    boolean checkIfSurveyPublished(String courseId);

    boolean checkIfSurveyHasFormula(String courseId);

    boolean publishSurvey(String courseId);

    int getSurveyIdByCourseId(String courseId);

	public String getSurveyId(String courseId);
	public ArrayList<IQuestion> getSurveyQuestions(String surveyId);
	public boolean isSurveyPublished(String surveyId);
	public boolean isSurveyCompleted(String surveyId, String userId);

}
