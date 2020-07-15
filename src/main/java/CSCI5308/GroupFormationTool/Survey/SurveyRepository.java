package CSCI5308.GroupFormationTool.Survey;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Course.ICourse;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Question.IQuestion;
import CSCI5308.GroupFormationTool.Question.IQuestionAbstractFactory;

public class SurveyRepository implements ISurveyRepository {
	
	@Override
    public int createSurvey(String courseId) {
        StoredProcedure storedProcedure = null;
        int surveyId = -1;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_createSurvey(?,?)");
            storedProcedure.setInputStringParameter(1, courseId);
            storedProcedure.registerOutputParameterLong(2);
            storedProcedure.execute();
            surveyId = (int) storedProcedure.getParameterLong(2);
        } catch (SQLException ex) {
        } finally {
            if (null != storedProcedure) {
                storedProcedure.removeConnections();
            }
        }
        return surveyId;
    }
	
	@Override
    public boolean addQuestionToSurvey(long questionId, long surveyId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        boolean status = true;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_addQuestionToSurvey(?,?,?)");
            storedProcedure.setInputIntParameter(1, surveyId);
            storedProcedure.setInputIntParameter(2, questionId);
            storedProcedure.registerOutputParameterBoolean(3);
            storedProcedure.execute();
            status = storedProcedure.getParameter(3);
        } catch (SQLException ex) {
        } finally {
            if (null != storedProcedure) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

	@Override
	public ArrayList<IQuestion> getQuestionsForSurvey(String courseId) {
		StoredProcedure storedProcedure = null;
		IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ArrayList<IQuestion> questionList = questionAbstractFactory.createQuestionListInstance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getSurveyQuestionsForCourse(?)");
            storedProcedure.setInputStringParameter(1, courseId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        IQuestion question = questionAbstractFactory.createQuestionInstance();
                        question.setId(results.getLong("question_id"));
                        question.setText(results.getString("question_text"));
                        question.setType(results.getInt("qtype_id"));
                        question.setTitle(results.getString("title"));
                        questionList.add(question);
                    }
                }
            }
        } catch (SQLException ex) {
            
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return questionList;
	}
	
	@Override
    public boolean deleteQuestionFromSurvey(long questionId, long surveyId) {
        StoredProcedure storedProcedure = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        boolean status = true;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_deleteSurveyQuestion(?,?)");
            storedProcedure.setInputIntParameter(1, surveyId);
            storedProcedure.setInputIntParameter(2, questionId);
            storedProcedure.execute();
        } catch (SQLException ex) {
        } finally {
            if (null != storedProcedure) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }
	
}
