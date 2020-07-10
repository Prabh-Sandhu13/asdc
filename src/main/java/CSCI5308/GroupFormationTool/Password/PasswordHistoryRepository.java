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

public class PasswordHistoryRepository implements IPasswordHistoryRepository {

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
            System.out.println(ex.getMessage());
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
            System.out.println(ex.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
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
            System.out.println("" + ex.getMessage());
        } finally {
            if (storedProcedure != null) {
                storedProcedure.removeConnections();
            }
        }
        return historyAdded;
    }
}
