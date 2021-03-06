package CSCI5308.GroupFormationTool.User;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository implements IUserRepository {

    @Override
    public boolean createUser(IUser user) {
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance(
                    "sp_create_user(?,?,?,?,?)");
            storedProcedure.setInputStringParameter(1, user.getBannerId());
            storedProcedure.setInputStringParameter(2, user.getFirstName());
            storedProcedure.setInputStringParameter(3, user.getLastName());
            storedProcedure.setInputStringParameter(4, user.getEmailId());
            storedProcedure.setInputStringParameter(5, user.getPassword());
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

    @Override
    public IUser getUserIdByEmailId(IUser user) {

        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        IUser userWithUserId = null;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getUserId(?)");
            storedProcedure.setInputStringParameter(1, user.getEmailId());
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        userWithUserId = userAbstractFactory.createUserInstance();
                        userWithUserId.setId(Long.parseLong(results.getString("user_id")));
                    }
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return userWithUserId;
    }

    @Override
    public IUser getUserByEmailId(IUser user) {

        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        IUser userByEmailId = null;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getUserByEmailId(?)");
            storedProcedure.setInputStringParameter(1, user.getEmailId());
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {

                while (results.next()) {
                    {
                        userByEmailId = userAbstractFactory.createUserInstance();
                        userByEmailId.setBannerId(results.getString("banner_id"));
                        userByEmailId.setEmailId(results.getString("email"));
                        userByEmailId.setFirstName(results.getString("first_name"));
                        userByEmailId.setLastName(results.getString("last_name"));
                        userByEmailId.setPassword(results.getString("password"));
                    }
                }
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return userByEmailId;
    }

    @Override
    public IUser getAdminDetails() {

        IUserAbstractFactory userAbstractFactory = Injector.instance().getUserAbstractFactory();
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        IUser adminDetails = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getAdminDetails");
            ResultSet results = storedProcedure.executeWithResults();

            if (results != null) {
                while (results.next()) {
                    {
                        adminDetails = userAbstractFactory.createUserInstance();
                        adminDetails.setBannerId(results.getString("banner_id"));
                        adminDetails.setEmailId(results.getString("email"));
                        adminDetails.setFirstName(results.getString("first_name"));
                        adminDetails.setLastName(results.getString("last_name"));
                    }
                }
            }

        } catch (SQLException ex) {
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return adminDetails;
    }
}
