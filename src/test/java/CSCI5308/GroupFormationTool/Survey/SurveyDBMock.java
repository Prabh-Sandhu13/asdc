package CSCI5308.GroupFormationTool.Survey;

import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Question.IQuestion;

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

	@Override
	public String getSurveyId(String courseId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<IQuestion> getSurveyQuestions(String surveyId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isSurveyPublished(String surveyId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSurveyCompleted(String surveyId, String userId) {
		// TODO Auto-generated method stub
		return false;
	}
}
