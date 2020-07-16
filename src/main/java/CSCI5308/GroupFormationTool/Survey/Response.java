package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionAdminRepository;
import CSCI5308.GroupFormationTool.Question.QuestionInjector;
import CSCI5308.GroupFormationTool.User.IUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Response implements IResponse {

    private long userId;

    private long surveyId;

    private long questionId;

    private String questionTitle;

    private String questionText;

    private String optionId;

    private List<String> options;

    private String answerText;

    private int questionType;

    private IResponseRepository responseRepository;

    private ISurveyRepository surveyRepository;

    private IQuestionAdminRepository questionAdminRepository;

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSurveyId() {
        return surveyId;
    }

    public void setSurveyId(long surveyId) {
        this.surveyId = surveyId;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(long questionId) {
        this.questionId = questionId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }

    public ArrayList<IResponse> createResponseList(Map<String, String> studentResponse) {
        responseRepository = SurveyInjector.instance().getResponseRepository();
        surveyRepository = SurveyInjector.instance().getSurveyRepository();
        questionAdminRepository = QuestionInjector.instance().getQuestionAdminRepository();
        ISurveyAbstractFactory surveyAbstractFactory = SurveyInjector.instance().getSurveyAbstractFactory();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        ArrayList<IResponse> responseList = surveyAbstractFactory.createResponseListInstance();
        IUser user = responseRepository.getResponseUser(authentication.getPrincipal().toString());
        int iteratorIndex = 0;
        String surveyId = null;
        for (Map.Entry<String, String> entry : studentResponse.entrySet()) {
            iteratorIndex++;
            if (iteratorIndex == 2) {
                surveyId = surveyRepository.getSurveyId(entry.getValue());
            } else if (iteratorIndex > 2) {
                IResponse response = surveyAbstractFactory.createResponseInstance();
                String[] responseValues = entry.getKey().split("_");
                IQuestion question = questionAdminRepository.getQuestionById(Long.parseLong(responseValues[1]));
                response.setQuestionId(Long.parseLong(responseValues[1]));
                response.setSurveyId(Long.parseLong(surveyId));
                response.setQuestionType(question.getType());
                response.setUserId(user.getId());
                if (question.getType() == DomainConstants.MCQMultiple || question.getType() == DomainConstants.MCQOne) {
                    response.setOptionId(responseRepository.getResponseOptionId(
                            Long.parseLong(responseValues[1]), responseValues[3]) + "");
                } else {
                    response.setOptionId("0");
                    response.setAnswerText(entry.getValue());
                }
                responseList.add(response);
            }
        }

        return responseList;
    }

    public boolean storeResponses(ArrayList<IResponse> responseList) {
        responseRepository = SurveyInjector.instance().getResponseRepository();
        return responseRepository.storeResponses(responseList);

    }

    public IUser getResponseUser(String emailId) {
        responseRepository = SurveyInjector.instance().getResponseRepository();
        return responseRepository.getResponseUser(emailId);
    }

}
