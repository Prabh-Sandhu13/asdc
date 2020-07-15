package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Question.IQuestion;

import java.util.ArrayList;

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
        return "1";
    }

    @Override
    public ArrayList<IQuestion> getSurveyQuestions(String surveyId) {
        return null;
    }

    @Override
    public boolean isSurveyPublished(String surveyId) {
        return false;
    }

    @Override
    public boolean isSurveyCompleted(String surveyId, String userId) {
        return false;
    }

    @Override
    public int createSurvey(String courseId) {
        return 0;
    }

    @Override
    public boolean addQuestionToSurvey(long questionId, long surveyId) {
        return false;
    }

    @Override
    public ArrayList<IQuestion> getQuestionsForSurvey(String courseId) {
        return null;
    }

    @Override
    public boolean deleteQuestionFromSurvey(long questionId, long surveyId) {
        return false;
    }

    @Override
    public ArrayList<IQuestion> getSurveyQuestionListForInstructor(String emailId, int surveyId, String questionTitle) {
        return null;
    }

    @Override
    public ArrayList<IQuestion> getSurveyQuestionListForTA(ArrayList<Long> instructorIds, int surveyId,
                                                           String questionTitle) {
        return null;
    }
}
