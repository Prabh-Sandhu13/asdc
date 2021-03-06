package CSCI5308.GroupFormationTool.Password;

import CSCI5308.GroupFormationTool.Common.Injector;
import CSCI5308.GroupFormationTool.Database.IDatabaseAbstractFactory;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.User.IUser;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordHistoryRepository implements IPasswordHistoryRepository {

	private static final Logger Log = LoggerFactory.getLogger(PasswordHistoryRepository.class.getName());
	
    @Override
    public String getSettingValue(String settingName) {
        String settingValue = null;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getSettingvalue(?)");
            storedProcedure.setInputStringParameter(1, settingName);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        settingValue = results.getString("pSetting_value");
                    }
                }
            }
        } catch (SQLException ex) {
            Log.error("Could not execute the Stored procedure sp_getsettingvalue" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return settingValue;
    }

    @Override
    public ArrayList<String> getNPasswords(IUser user, String num) {
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        IPasswordAbstractFactory passwordAbstractFactory = Injector.instance().getPasswordAbstractFactory();
        ArrayList<String> nPasswords = passwordAbstractFactory.createPasswordList();
        StoredProcedure storedProcedure = null;
        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance("sp_getNPasswords(?,?)");
            storedProcedure.setInputStringParameter(1, "" + user.getId());
            storedProcedure.setInputStringParameter(2, "" + num);
            ResultSet results = storedProcedure.executeWithResults();
            if (results != null) {
                while (results.next()) {
                    {
                        nPasswords.add(results.getString("encrypted_password"));
                    }
                }
            }
        } catch (SQLException ex) {
            Log.error("Could not execute the Stored procedure sp_getNPassword" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } catch (Exception e) {
            Log.error("Could not DB query due to non SQL Exception" + e.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return nPasswords;
    }

    @Override
    public boolean addPasswordHistory(IUser user, String password) {
        boolean historyAdded = false;
        IDatabaseAbstractFactory databaseAbstractFactory = Injector.instance().getDatabaseAbstractFactory();
        IPasswordAbstractFactory passwordAbstractFactory = Injector.instance().getPasswordAbstractFactory();
        StoredProcedure storedProcedure = null;
        Date currentDate = passwordAbstractFactory.createDateInstance();
        SimpleDateFormat dateTimeFormat = passwordAbstractFactory.createSimpleDateFormatInstance();

        try {
            storedProcedure = databaseAbstractFactory.createStoredProcedureInstance
                    ("sp_addPasswordHistory(?,?,?)");
            storedProcedure.setInputStringParameter(1, Long.toString(user.getId()));
            storedProcedure.setInputStringParameter(2, dateTimeFormat.format(currentDate));
            storedProcedure.setInputStringParameter(3, password);
            storedProcedure.execute();
            historyAdded = true;
        } catch (SQLException ex) {
            Log.error("Could not execute the Stored procedure sp_addPasswordHistory" +
                    " because of an SQL Exception " + ex.getLocalizedMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return historyAdded;
    }
}
