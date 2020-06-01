package CSCI5308.GroupFormationTool.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import CSCI5308.GroupFormationTool.AccessControl.IForgotPasswordRepository;
import CSCI5308.GroupFormationTool.Database.StoredProcedure;
import CSCI5308.GroupFormationTool.Model.User;

public class ForgotPasswordRepository implements IForgotPasswordRepository{

	@Override
	public boolean addToken(User user,String token) {
		
		boolean tokenAdded = false;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_addToken(?,?,?)");
			storedProcedure.setInputStringParameter(1, Long.toString(user.getId()));
			storedProcedure.setInputStringParameter(2, user.getEmailId());
			storedProcedure.setInputStringParameter(3, token);
			
			storedProcedure.execute();
			tokenAdded = true;

		}
		catch (SQLException ex) {
			System.out.println(""+ex.getMessage());

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return tokenAdded;

	}

	@Override
	public String getToken(User user) {
		
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

		}
		catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return token;
	}

	@Override
	public boolean updatePassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteToken(User user, String token) {
		boolean tokenDeleted = false;
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_deleteToken(?)");
			storedProcedure.setInputStringParameter(1, token);
			storedProcedure.execute();
			tokenDeleted = true;

		}
		catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return tokenDeleted;
	}

	@Override
	public boolean updateToken(User user, String token) {
		boolean tokenUpdated = false;
	//	System.out.println("In update");
		StoredProcedure storedProcedure = null;
		try {
			storedProcedure = new StoredProcedure("sp_updateToken(?,?)");
			storedProcedure.setInputStringParameter(1, user.getEmailId());
			storedProcedure.setInputStringParameter(2, token);
			storedProcedure.execute();
			tokenUpdated = true;

		}
		catch (SQLException ex) {

		} finally {
			if (storedProcedure != null) {
				storedProcedure.removeConnections();
			}
		}
		return tokenUpdated;
	}

	@Override
	public User getUserId(User user) {

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

}
