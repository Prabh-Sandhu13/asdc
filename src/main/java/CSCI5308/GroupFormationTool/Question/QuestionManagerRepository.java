package CSCI5308.GroupFormationTool.Question;

import java.sql.SQLException;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

public class QuestionManagerRepository implements IQuestionManagerRepository {

    @Override
    public long createQuestion(IQuestion question) {

    	IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        long questionId = -1;

        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_createQuestion(?,?,?,?,?)");
            storedProcedure.setInputStringParameter(1, question.getTitle());
            storedProcedure.setInputStringParameter(2, question.getText());
            storedProcedure.setInputStringParameter(3, question.getInstructor().getEmailId());
            storedProcedure.setInputIntParameter(4, question.getType());
            storedProcedure.registerOutputParameterLong(5);
            storedProcedure.execute();
            questionId = storedProcedure.getParameterLong(5);

        } catch (SQLException ex) {
            return -1;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        if (question.getChoices() != null) {
            for (IChoice choice : question.getChoices()) {
                if (!saveChoice(choice, questionId)) {
                    return -1;
                }
            }

        }
        return questionId;
    }

    private boolean saveChoice(IChoice choice, long questionId) {

        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;

        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_saveOptions(?,?,?)");

            storedProcedure.setInputStringParameter(1, choice.getText());
            storedProcedure.setInputIntParameter(2, choice.getValue());
            storedProcedure.setInputIntParameter(3, questionId);
            storedProcedure.execute();

        } catch (SQLException ex) {
            return false;
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return true;
    }
    public boolean deleteQuestion(long questionId) {

        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();

        StoredProcedure storedProcedure = null;
        boolean status = true;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_deleteAQuestion(?,?)");
            storedProcedure.setInputIntParameter(1, questionId);
            storedProcedure.registerOutputParameterBoolean(2);
            storedProcedure.execute();

            status = storedProcedure.getParameter(2);

        } catch (SQLException ex) {
            System.out.println(ex);
        } finally {
            if (null != storedProcedure) {
                storedProcedure.removeConnections();
            }
        }
        return status;
    }

}
