package CSCI5308.GroupFormationTool.Question;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QuestionAdminRepository implements IQuestionAdminRepository {

    @Override
    public ArrayList<IQuestion> getQuestionListForInstructor(String emailId) {
        IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        ArrayList<IQuestion> questionList = questionAbstractFactory.createQuestionListInstance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getQuestionsForInstructor(?)");
            storedProcedure.setInputStringParameter(1, emailId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        IQuestion question = questionAbstractFactory.createQuestionInstance();
                        question.setId(results.getLong("question_id"));
                        question.setText(results.getString("question_text"));
                        question.setType(results.getInt("qtype_id"));
                        question.setTitle(results.getString("title"));
                        question.setCreatedDate(results.getDate("created_date"));
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
    public IQuestion getQuestionById(long questionId) {
        StoredProcedure storedProcedure = null;
        IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        IQuestion question = questionAbstractFactory.createQuestionInstance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getQuestionById(?)");
            storedProcedure.setInputIntParameter(1, questionId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        question.setId(results.getLong("question_id"));
                        question.setText(results.getString("question_text"));
                        question.setType(results.getInt("qtype_id"));
                        question.setTitle(results.getString("title"));
                        question.setCreatedDate(results.getDate("created_date"));
                    }
                }
            }

        } catch (SQLException ex) {
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return question;
    }

    @Override
    public ArrayList<IChoice> getOptionsForTheQuestion(long questionId) {
        StoredProcedure storedProcedure = null;
        IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ArrayList<IChoice> choiceList = questionAbstractFactory.createChoiceListInstance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getOptionsForQuestion(?)");
            storedProcedure.setInputIntParameter(1, questionId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        IChoice choice = questionAbstractFactory.createChoiceInstance();
                        choice.setText(results.getString("options_text"));
                        choice.setValue(results.getInt("options_value"));
                        choiceList.add(choice);
                    }
                }
            }
        } catch (SQLException ex) {
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return choiceList;
    }


    @Override

    public ArrayList<IQuestion> getSortedQuestionListForInstructor(String emailId, String sortBy) {
        StoredProcedure storedProcedure = null;
        IQuestionAbstractFactory questionAbstractFactory = Injector.instance().getQuestionAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        ArrayList<IQuestion> questionList = questionAbstractFactory.createQuestionListInstance();
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_getSortedQuestionsForInstructor(?,?)");
            storedProcedure.setInputStringParameter(1, emailId);
            storedProcedure.setInputStringParameter(2, sortBy);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        IQuestion question = questionAbstractFactory.createQuestionInstance();
                        question.setId(results.getLong("question_id"));
                        question.setText(results.getString("question_text"));
                        question.setType(results.getInt("qtype_id"));
                        question.setTitle(results.getString("title"));
                        question.setCreatedDate(results.getDate("created_date"));
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
}
