package CSCI5308.GroupFormationTool.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.AccessControl.IUser;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.User;

public class ForgotPasswordRepository implements IForgotPasswordRepository {

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
		// System.out.println("In update");
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

		User userByEmailId = null;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_getUserId(?)");
			storedProcedure.setInputStringParameter(1, user.getEmailId());
			ResultSet results = storedProcedure.executeWithResults();

			if (results != null) {

				while (results.next()) {
					{
						userByEmailId = new User();
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
		User userByEmailId = null;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_getEmailByToken(?)");
			storedProcedure.setInputStringParameter(1, token);
			ResultSet results = storedProcedure.executeWithResults();
			if (results != null) {

				while (results.next()) {
					{
						userByEmailId = new User();
						userByEmailId.setId(Long.parseLong(results.getString("user_id")));
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

	@Override
	public String getSettingValue(String settingName) {
		String settingValue = null;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_getSettingvalue(?)");
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
		
		ArrayList<String> nPasswords= new ArrayList<String>();
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_getNPasswords(?,?)");
			storedProcedure.setInputStringParameter(1, ""+user.getId());
			storedProcedure.setInputStringParameter(2, ""+num);
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

		} 
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		
		
		
		return nPasswords;
	}

	@Override
	public boolean addPasswordHistory(IUser user, String password) {
		boolean historyAdded = false;
		StoredProcedure storedProcedure = null;
		Date currentDate = new Date();
		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			storedProcedure = new StoredProcedure("sp_addPasswordHistory(?,?,?)");
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
