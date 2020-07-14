package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.Map;

public interface IResponse {
	public long getUserId();

	public void setUserId(long userId);

	public long getSurveyId();

	public void setSurveyId(long surveyId);

	public long getQuestionId();

	public void setQuestionId(long questionId);

	public String getOptionId();

	public void setOptionId(String optionId);

	public String getAnswerText();

	public void setAnswerText(String answerText);

	public int getQuestionType();

	public void setQuestionType(int qusetionType);
	
	public ArrayList<IResponse> createResponseList(Map<String,String> studentResponse);
	
	public boolean storeResponses(ArrayList<IResponse> responseList);
}
