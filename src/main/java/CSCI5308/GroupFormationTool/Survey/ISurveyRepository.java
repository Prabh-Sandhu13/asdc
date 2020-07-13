package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

public interface ISurveyRepository {
	public String getSurveyId(String courseId);
	public ArrayList<IQuestion> getSurveyQuestions(String surveyId);
}
