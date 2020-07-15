package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.User.IUser;

import java.util.ArrayList;
import java.util.List;
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

    public List<String> getOptions();

    public void setOptions(List<String> options);

    public String getAnswerText();

    public void setAnswerText(String answerText);

    public int getQuestionType();

    public void setQuestionType(int qusetionType);

    public ArrayList<IResponse> createResponseList(Map<String, String> studentResponse);

    public boolean storeResponses(ArrayList<IResponse> responseList);

    public IUser getResponseUser(String emailId);

    public String getQuestionTitle();

    public void setQuestionTitle(String questionTitle);

    public String getQuestionText();

    public void setQuestionText(String questionText);

}
