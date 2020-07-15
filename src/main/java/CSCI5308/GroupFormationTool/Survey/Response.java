package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionAdminRepository;
import CSCI5308.GroupFormationTool.User.IUser;

public class Response implements IResponse {
	private long userId;

	private long surveyId;

	private long questionId;

	private String optionId;

	private String answerText;

	private int questionType;

	private IResponseRepository responseRepository;

	private ISurveyRepository surveyRepository;
	
	private IQuestionAdminRepository questionAdminRepository;

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

	public void setQuestionType(int qusetionType) {
		this.questionType = qusetionType;
	}

	public ArrayList<IResponse> createResponseList(Map<String, String> studentResponse) {
		responseRepository = Injector.instance().getResponseRepository();
		surveyRepository = Injector.instance().getSurveyRepository();
        questionAdminRepository = Injector.instance().getQuestionAdminRepository();
		ISurveyAbstractFactory surveyAbstractFactory = Injector.instance().getSurveyAbstractFactory();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ArrayList<IResponse> responseList = surveyAbstractFactory.createResponseListInstance();
		IUser user = responseRepository.getResponseUser(authentication.getPrincipal().toString());
		int iteratorIndex = 0;
		String surveyId = null;
		for (Map.Entry<String, String> entry : studentResponse.entrySet()) {
			iteratorIndex++;
			if(iteratorIndex == 2) {
				surveyId = surveyRepository.getSurveyId(entry.getValue());
			}
			else if(iteratorIndex>2) {
				IResponse response = surveyAbstractFactory.createResponseInstance();
				String[] responseValues = entry.getKey().split("_");
				IQuestion question = questionAdminRepository.getQuestionById(Long.parseLong(responseValues[1]));
				
				response.setQuestionId(Long.parseLong(responseValues[1]));
				response.setSurveyId(Long.parseLong(surveyId));
				response.setQuestionType(question.getType());
				response.setUserId(user.getId());				
		        if (question.getType() == DomainConstants.MCQMultiple || question.getType() == DomainConstants.MCQOne) {
		            response.setOptionId(responseRepository.getResponseOptionId(
		            		Long.parseLong(responseValues[1]),responseValues[3])+"");
		        }
		        else {
		        	response.setOptionId("0");
		        	response.setAnswerText(entry.getValue());
		        }
		        responseList.add(response);
			}
		}

		return responseList;
	}
	public boolean storeResponses(ArrayList<IResponse> responseList) {
		responseRepository = Injector.instance().getResponseRepository();
		return responseRepository.storeResponses(responseList);
		
	}
	
	public IUser getResponseUser(String emailId) {
		responseRepository = Injector.instance().getResponseRepository();
		return responseRepository.getResponseUser(emailId);
	}

}
