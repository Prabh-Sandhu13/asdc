package CSCI5308.GroupFormationTool.Survey;

import CSCI5308.GroupFormationTool.Common.DomainConstants;
import CSCI5308.GroupFormationTool.Database.DatabaseInjector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.User.UserInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResponseRepository implements IResponseRepository {
    private static final Logger log = LoggerFactory.getLogger(ResponseRepository.class.getName());

    @Override
    public IUser getResponseUser(String emailId) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        IUserAbstractFactory userAbstractFactory = UserInjector.instance().getUserAbstractFactory();
        IUser userByEmailId = null;
        StoredProcedure storedProcedure = null;
        try {
            log.info("Calling stored procedure sp_getUserId to get user details by email Id" + emailId);
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getUserId(?)");
            storedProcedure.setInputStringParameter(1, emailId);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        userByEmailId = userAbstractFactory.createUserInstance();
                        userByEmailId.setId(Long.parseLong(results.getString("user_id")));
                        userByEmailId.setBannerId(results.getString("banner_id"));
                        userByEmailId.setEmailId(results.getString("email"));
                        userByEmailId.setFirstName(results.getString("first_name"));
                        userByEmailId.setLastName(results.getString("last_name"));
                        userByEmailId.setPassword(results.getString("password"));
                    }
                }
            }
        } catch (SQLException ex) {
            log.error("Could not execute the Stored procedure sp_getUserId" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return userByEmailId;
    }

    @Override
    public long getResponseOptionId(long questionId, String optionText) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        long optionId = 0;
        try {
            log.info("Calling stored procedure sp_getResponseOptionId to get response optionId for question " + questionId + "and option text " + optionText);
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getResponseOptionId(?,?)");
            storedProcedure.setInputStringParameter(1, "" + questionId);
            storedProcedure.setInputStringParameter(2, optionText);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        optionId = Integer.parseInt(results.getString("option_id"));
                    }
                }
            }
        } catch (SQLException ex) {
            log.error("Could not execute the Stored procedure sp_getResponseOptionId" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return optionId;
    }

    @Override
    public boolean storeResponses(ArrayList<IResponse> responseList) {
        IDatabaseAbstractFactory databaseAbstractFactory = DatabaseInjector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;

        for (IResponse singleResponse : responseList) {
            try {
                log.info("Calling stored procedure sp_addResponse to add/save response to the database");
                storedProcedure = databaseAbstractFactory.
                        createStoredProcedureInstance("sp_addResponse(?,?,?,?,?)");
                storedProcedure.setInputStringParameter(1, singleResponse.getUserId() + "");
                storedProcedure.setInputStringParameter(2, singleResponse.getSurveyId() + "");
                storedProcedure.setInputStringParameter(3, singleResponse.getQuestionId() + "");
                storedProcedure.setInputStringParameter(4, singleResponse.getOptionId());
                if (singleResponse.getQuestionType() == DomainConstants.MCQMultiple ||
                        singleResponse.getQuestionType() == DomainConstants.MCQOne) {
                    storedProcedure.setInputStringParameter(5, "");
                } else {
                    storedProcedure.setInputStringParameter(5, singleResponse.getAnswerText());
                }
                storedProcedure.execute();
            } catch (SQLException ex) {
                log.error("Could not execute the Stored procedure sp_addResponse" +
                        " because of an SQL Exception " + ex.getLocalizedMessage());
            } finally {
                if (storedProcedure != null) {
                    storedProcedure.removeConnections();
                }
            }
        }
        return true;
    }

}
