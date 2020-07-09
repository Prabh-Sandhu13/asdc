package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.User.IUser;
import CSCI5308.GroupFormationTool.User.IUserAbstractFactory;
import CSCI5308.GroupFormationTool.Common.FactoryProducer;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgotPasswordRepository implements IForgotPasswordRepository {

    private IUserAbstractFactory userAbstractFactory = FactoryProducer.
        getFactory().createUserAbstractFactory();
    @Override
    public boolean addToken(IUser user, String token) {

        boolean tokenAdded = false;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("sp_addToken(?,?,?)");
            storedProcedure.setInputStringParameter(1, Long.toString(user.getId()));
            storedProcedure.setInputStringParameter(2, user.getEmailId());
            storedProcedure.setInputStringParameter(3, token);

            storedProcedure.execute();
            tokenAdded = true;

        } catch (SQLException ex) {
            System.out.println("" + ex.getMessage());

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return tokenAdded;

    }

    @Override
    public String getToken(IUser user) {

        String token = "";
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("sp_getTokenByEmailId(?)");
            storedProcedure.setInputStringParameter(1, user.getEmailId());
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {

                while (results.next()) {
                    {
                        token = (results.getString("temporary_lik"));
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
        return token;
    }

    @Override
    public boolean updatePassword(IUser user, String password) {

        boolean passwordUpdated = false;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("sp_updatePassword(?,?)");
            storedProcedure.setInputStringParameter(1, user.getEmailId());
            storedProcedure.setInputStringParameter(2, password);
            storedProcedure.execute();
            passwordUpdated = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return passwordUpdated;

    }

    @Override
    public boolean deleteToken(IUser user, String token) {
        boolean tokenDeleted = false;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("sp_deleteToken(?)");
            storedProcedure.setInputStringParameter(1, token);
            storedProcedure.execute();
            tokenDeleted = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return tokenDeleted;
    }

    @Override
    public boolean updateToken(IUser user, String token) {
        boolean tokenUpdated = false;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("sp_updateToken(?,?)");
            storedProcedure.setInputStringParameter(1, user.getEmailId());
            storedProcedure.setInputStringParameter(2, token);
            storedProcedure.execute();
            tokenUpdated = true;

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return tokenUpdated;
    }

    @Override
    public IUser getUserId(IUser user) {

        IUser userByEmailId = null;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("sp_getUserId(?)");
            storedProcedure.setInputStringParameter(1, user.getEmailId());
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

            System.out.println(ex.getMessage());

        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return userByEmailId;

    }

    @Override
    public IUser getEmailByToken(IUser user, String token) {
        IUser userByEmailId = null;
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = new StoredProcedure("sp_getEmailByToken(?)");
            storedProcedure.setInputStringParameter(1, token);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {

                while (results.next()) {
                    {
                        userByEmailId = userAbstractFactory.createUserInstance();                        userByEmailId.setId(Long.parseLong(results.getString("user_id")));
                        userByEmailId.setEmailId(results.getString("email"));
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

}
