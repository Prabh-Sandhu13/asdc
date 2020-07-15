package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurvey {


    boolean checkIfSurveyCreated(String courseId);

    boolean checkIfSurveyPublished(String courseId);

    boolean checkIfSurveyHasFormula(String courseId);

    int checkIfGroupsCanBeFormedForSurvey(String courseId);

    boolean publishSurvey(String courseId);

    int getSurveyIdByCourseId(String courseId);

	public String getSurveyId(String courseId);
	
	public ArrayList<IQuestion> getSurveyQuestions(String surveyId);
	
	public boolean isSurveyPublished(String courseId);
	
	public boolean isSurveyCompleted(String courseId, String userId);

}
